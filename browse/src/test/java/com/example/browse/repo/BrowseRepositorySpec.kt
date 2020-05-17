package com.example.browse.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.core.api.ApiSuccessResponse
import com.example.core.api.LCBOApiService
import com.example.core.data.LCBOItemDao
import com.example.core.data.QueryOptionsMapConverter
import com.example.core.data.SupportSQLiteQueryConverter
import com.example.core.vo.LCBOItem
import com.example.core.vo.LCBOItemQueryParameters
import com.example.core.vo.Resource
import com.example.core.vo.Status
import com.example.core_test.createTestLCBOItem
import com.example.core_test.testRules.MainCoroutineScopeRule
import com.example.core_test.testRules.withTestRules
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldContainAll
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class BrowseRepositorySpec : Spek({

    val instantTaskExecutorRule = InstantTaskExecutorRule()
    val coroutinesScope = MainCoroutineScopeRule()
    withTestRules(instantTaskExecutorRule, coroutinesScope)

    describe("the BrowseRepository") {
        val dao: LCBOItemDao = mockk(relaxed = true)
        val sqlConverter: SupportSQLiteQueryConverter = mockk(relaxed = true)
        val queryConverter: QueryOptionsMapConverter = mockk(relaxed = true)
        val lcboApiService: LCBOApiService = mockk()
        val browseRepository = BrowseRepository(dao, sqlConverter, queryConverter, lcboApiService)

        describe("search") {
            describe("fetch a successful result from network") {
                coroutinesScope.runBlockingTest {
                    val queryParameters: LCBOItemQueryParameters = mockk()
                    val resultsChannel = ConflatedBroadcastChannel<List<LCBOItem>>()

                    // Provide fake results from the local database
                    val dbData = listOf(createTestLCBOItem(12))
                    resultsChannel.offer(dbData)
                    every { dao.getLcboItems(any()) } returns resultsChannel.asFlow()

                    // Network returns a successful response
                    val networkData = listOf(createTestLCBOItem(42))
                    coEvery {
                        lcboApiService.fetchLcboItems(any())
                    } returns ApiSuccessResponse(networkData)

                    // Inserting data will cause Room to emit the new combined data
                    every {
                        coroutinesScope.runBlockingTest {
                            dao.insert(networkData)
                        }
                    } answers {
                        resultsChannel.offer(dbData + networkData)
                    }

                    lateinit var lastCollectedResult: Resource<List<LCBOItem>>
                    launch {
                        // Must use a terminal operation to make the flow emit
                        browseRepository.search(queryParameters).collect { result ->
                            lastCollectedResult = result
                        }
                    }

                    it("it queries the local data base TWICE") {
                        // Called twice: 1st to check cache, 2nd after network data has been added
                        verify(exactly = 2) {
                            browseRepository.lcboItemDao.getLcboItems(any())
                        }
                    }

                    it("calls the api service to fetch items") {
                        coVerify(exactly = 1) {
                            browseRepository.lcboApiService.fetchLcboItems(any())
                        }
                    }

                    it("inserts the network data into the database") {
                        verify(exactly = 1) {
                            coroutinesScope.runBlockingTest {
                                browseRepository.lcboItemDao.insert(networkData)
                            }
                        }
                    }

                    it("returns the combined results of local data and network as a success") {
                        lastCollectedResult.data?.shouldContainAll(networkData + dbData)
                        lastCollectedResult.status.shouldBeInstanceOf(Status.SUCCESS::class.java)
                    }

                    resultsChannel.close()
                }
            }
            describe("unsuccessful fetch from network") {}
            describe("use cache instead of fetching from network") {}
        }
    }
})