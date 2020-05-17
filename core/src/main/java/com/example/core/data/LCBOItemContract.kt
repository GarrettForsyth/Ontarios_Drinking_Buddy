package com.example.core.data

object LCBOItemContract {
    const val TABLE_NAME = "lcbo_items"
    const val COLUMN_NAME = "name"
    const val COLUMN_PRIMARY_CATEGORY = "primary_category"
    const val COLUMN_SECONDARY_CATEGORY = "secondary_category"
    const val COLUMN_TERTIARY_CATEGORY = "tertiary_category"
    const val COLUMN_TAGS = "tags"

    // The fields that should be checked when searching by a filter string
    val SEARCH_FIELDS = listOf(
        COLUMN_NAME,
        COLUMN_TAGS,
        COLUMN_PRIMARY_CATEGORY,
        COLUMN_SECONDARY_CATEGORY,
        COLUMN_TERTIARY_CATEGORY
    )
}