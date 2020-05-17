package com.example.core.network

import com.example.core.api.ApiResponse
import com.example.core.api.ApiSuccessResponse
import com.example.core.api.LCBOApiService
import com.example.core.api.adapters.ApiResponseCallAdapterFactory
import com.example.core.api.converters.EnvelopeConverterFactory
import com.example.core.vo.LCBOItem
import com.example.core_test.MockWebServerResponseUtils.enqueueServerWithFile
import com.example.core_test.MockWebServerResponseUtils.lcboItemResponseToList
import com.example.core_test.testRules.MainCoroutineScopeRule
import com.example.core_test.testRules.withTestRules
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.amshove.kluent.shouldBeEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LCBOApiServiceSpec : Spek({
    val coroutinesScope = MainCoroutineScopeRule()
    withTestRules(coroutinesScope)
    describe("LCBOApiService") {
        lateinit var mockWebServer: MockWebServer
        lateinit var lcboApiService: LCBOApiService
        beforeEachTest {
            mockWebServer = MockWebServer()
            lcboApiService = Retrofit.Builder()
                .addConverterFactory(EnvelopeConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(ApiResponseCallAdapterFactory())
                .baseUrl(mockWebServer.url("/"))
                .build()
                .create(LCBOApiService::class.java)
        }
        afterEachTest {
            mockWebServer.shutdown()
        }

        describe("fetchLcboItems") {
            val queryMap: Map<String, String> = mockk(relaxed = true)
            describe("a successful fetch") {
                it("it returns a list of LCBO items for the query") {
                    enqueueServerWithFile(mockWebServer, "search_beer.json")
                    lateinit var response: ApiResponse<List<LCBOItem>>
                    runBlocking {
                        response = lcboApiService.fetchLcboItems(queryMap)
                    }
                    val expectedResults = lcboItemResponseToList("expected_beer_results.json")
                    val results = (response as ApiSuccessResponse<List<LCBOItem>>).body
                    results.shouldBeEqualTo(expectedResults)
                }
            }
        }
    }
})

