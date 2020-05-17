package com.example.core.data

import com.example.core.vo.LCBOItemQueryParameters
import javax.inject.Inject

class QueryOptionsMapConverter @Inject constructor() {

    fun convert(params: LCBOItemQueryParameters): Map<String, String> {
        val queryOptions = mutableMapOf<String, String>()
        if (params.filterString.isNotEmpty()) {
            queryOptions[QUERY] = params.filterString
        }
        return queryOptions
    }

    companion object Options {
        private const val QUERY = "q"
    }
}