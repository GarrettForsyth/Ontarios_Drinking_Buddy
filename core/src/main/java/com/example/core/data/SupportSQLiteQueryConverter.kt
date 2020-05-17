package com.example.core.data

import androidx.sqlite.db.SupportSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQueryBuilder
import com.example.core.vo.LCBOItemQueryParameters
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SupportSQLiteQueryConverter @Inject constructor() {
    fun convert(params: LCBOItemQueryParameters): SupportSQLiteQuery {
        val queryBuilder = SupportSQLiteQueryBuilder.builder(LCBOItemContract.TABLE_NAME)

        val selection = StringBuilder("")
        val selectionArgs = Array(LCBOItemContract.SEARCH_FIELDS.size) {
            "%${params.filterString}%"
        }
        selection.append(
            """
            (name LIKE ?)
            OR (${LCBOItemContract.COLUMN_TAGS} LIKE ?)
            OR (${LCBOItemContract.COLUMN_PRIMARY_CATEGORY} LIKE ?)
            OR (${LCBOItemContract.COLUMN_SECONDARY_CATEGORY} LIKE ?)
            OR (${LCBOItemContract.COLUMN_TERTIARY_CATEGORY} LIKE ?)
        """.trimIndent()
        )

        return queryBuilder.selection(selection.toString(), selectionArgs).create()
    }
}
