package com.example.browse.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.test.filters.SmallTest
import com.example.browse.repo.BrowseRepository
import com.example.core.vo.LCBOItem
import com.example.core.vo.Resource
import com.example.core_test.ui.util.getValueBlocking
import com.example.core_test.testRules.MainCoroutineScopeRule
import com.example.core_test.testRules.withTestRules
import com.example.core_test.ui.util.MockUtils.mockObserverFor
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


@SmallTest
class BrowseViewModelSpec : Spek({

    val instantTaskExecutorRule = InstantTaskExecutorRule()
    val coroutinesScope = MainCoroutineScopeRule()
    withTestRules(instantTaskExecutorRule, coroutinesScope)

    describe(" a BrowseViewModel") {
        val browseRepo: BrowseRepository = mockk(relaxed = true)
        val browseViewModel by memoized { BrowseViewModel(browseRepo) }

        describe("search") {
            val liveDataResults = MutableLiveData<Resource<List<LCBOItem>>>()
            beforeEachTest {
                mockObserverFor(browseViewModel.searchResults)

                mockkStatic("androidx.lifecycle.FlowLiveDataConversions")
                coEvery {
                    browseViewModel
                        .browseRepository
                        .search(browseViewModel.queryParameters)
                        .asLiveData()
                } returns liveDataResults

                browseViewModel.search()
            }

            it("calls the BrowseRepository to search with queryParameters") {
                verify {
                    browseViewModel
                        .browseRepository
                        .search(browseViewModel.queryParameters)
                }
            }

            it("updates searchResults with the search results") {
                val lcboItemResults: Resource<List<LCBOItem>> = mockk()
                liveDataResults.value = lcboItemResults

                browseViewModel
                    .searchResults
                    .getValueBlocking()
                    .shouldBeEqualTo(lcboItemResults)
            }
        }
    }
})