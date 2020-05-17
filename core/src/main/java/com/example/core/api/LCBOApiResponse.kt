package com.example.core.api

import com.google.gson.annotations.SerializedName

data class LCBOApiResponse<T>(
    @SerializedName("status")
    val status: Int,

    @SerializedName("message")
    val message: String?,

    @SerializedName("pager")
    val pager: Pager,

    @SerializedName("result")
    val result: T
)

data class Pager(
    @SerializedName("records_per_page")
    val recordsPerPage: Int,

    @SerializedName("total_record_count")
    val totalRecordCount: Int,

    @SerializedName("current_page_record_count")
    val currentPageRecordCount: Int,

    @SerializedName("is_first_page")
    val isFirstPage: Boolean,

    @SerializedName("is_final_page")
    val isFinalPage: Boolean,

    @SerializedName("current_page")
    val currentPage: Int,

    @SerializedName("current_page_path")
    val currentPagePath: String,

    @SerializedName("next_page")
    val nextPage: Int?,

    @SerializedName("next_page_path")
    val nextPagePath: String?,

    @SerializedName("previous_page")
    val previousPage: Int?,

    @SerializedName("previous_page_path")
    val previousPagePath: String?,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("total_pages_path")
    val totalPagesPath: String
)

