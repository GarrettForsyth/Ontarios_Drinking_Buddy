package com.example.browse.repo

import com.example.core.api.ApiResponse
import com.example.core.api.LCBOApiService
import com.example.core.data.LCBOItemDao
import com.example.core.data.QueryOptionsMapConverter
import com.example.core.data.SupportSQLiteQueryConverter
import com.example.core.idling.EspressoIdlingResource
import com.example.core.vo.LCBOItem
import com.example.core.vo.LCBOItemQueryParameters
import com.example.core.vo.NetworkBoundResource
import com.example.core.vo.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BrowseRepository @Inject constructor(
    val lcboItemDao: LCBOItemDao,
    val supportSQLiteQueryConverter: SupportSQLiteQueryConverter,
    val optionsMapConverter: QueryOptionsMapConverter,
    val lcboApiService: LCBOApiService
) {
    fun search(
        queryParameters: LCBOItemQueryParameters
    ): Flow<Resource<List<LCBOItem>>> {
        return object : NetworkBoundResource<List<LCBOItem>, List<LCBOItem>>() {
            override suspend fun loadFromDb(): Flow<List<LCBOItem>> {
                val sqlQuery = supportSQLiteQueryConverter.convert(queryParameters)
                return lcboItemDao.getLcboItems(sqlQuery)
            }

            override suspend fun saveNetworkResult(item: List<LCBOItem>) {
                lcboItemDao.insert(item)
            }

            override fun shouldFetch(data: List<LCBOItem>?): Boolean {
                return true
            }

            override suspend fun fetchFromNetwork(): ApiResponse<List<LCBOItem>> {
                val options = optionsMapConverter.convert(queryParameters)

                // TODO: Extract testing logic into a separate build flavour
                EspressoIdlingResource.increment()
                val results = lcboApiService.fetchLcboItems(options)
                EspressoIdlingResource.decrement()
                return results
            }

        }.asFlow()
    }
}