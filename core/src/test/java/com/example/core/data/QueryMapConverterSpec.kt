package com.example.core.data

import androidx.test.filters.SmallTest
import com.example.core.vo.LCBOItemQueryParameters
import org.amshove.kluent.shouldBeEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

@SmallTest
class QueryMapConverterSpec : Spek({
    describe("a QueryMapConverter") {
        val converter by memoized { QueryOptionsMapConverter() }
        describe("convert") {
            describe("converting a query string into a query map") {
                lateinit var queryMap: Map<String, String>
                beforeEachTest {
                    val queryParameters = LCBOItemQueryParameters(filterString = "beer")
                    queryMap = converter.convert(queryParameters)
                }
                it("it returns a map of the query parameters") {
                    val expected = mutableMapOf<String, String>().apply { put("q", "beer") }
                    queryMap.shouldBeEqualTo(expected)
                }
            }

        }
    }
})
