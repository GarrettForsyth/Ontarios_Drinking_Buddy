package com.example.core.data

import androidx.sqlite.db.SupportSQLiteQuery
import androidx.test.filters.SmallTest
import com.example.core.vo.LCBOItemQueryParameters
import org.amshove.kluent.shouldBeEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

@SmallTest
class SupportSQLiteQueryConverterSpec : Spek({
    describe("a SupportSQLiteConverter") {
        val converter by memoized { SupportSQLiteQueryConverter() }
        describe("convert") {
            describe("converting LCBOItemParameters with only a filter string") {
                lateinit var query: SupportSQLiteQuery
                beforeEachTest {
                    val parameters = LCBOItemQueryParameters(filterString = "beer")
                    query = converter.convert(parameters)
                }
                it("is translated into a SQL query matching") {
                    val expected =
                        """
                        SELECT  *  FROM ${LCBOItemContract.TABLE_NAME} WHERE (${LCBOItemContract.COLUMN_NAME} LIKE ?)
                        OR (${LCBOItemContract.COLUMN_TAGS} LIKE ?)
                        OR (${LCBOItemContract.COLUMN_PRIMARY_CATEGORY} LIKE ?)
                        OR (${LCBOItemContract.COLUMN_SECONDARY_CATEGORY} LIKE ?)
                        OR (${LCBOItemContract.COLUMN_TERTIARY_CATEGORY} LIKE ?)
                        """.trimIndent()
                    query.sql.shouldBeEqualTo(expected)
                }
                it("contains only 1 selection argument") {
                    query.argCount.shouldBeEqualTo(LCBOItemContract.SEARCH_FIELDS.size)
                }
            }
        }
    }
})