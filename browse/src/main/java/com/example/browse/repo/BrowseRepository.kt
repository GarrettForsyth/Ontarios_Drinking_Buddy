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
import timber.log.Timber
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
                Timber.tag("Idling").d("Increment - load from db")
                EspressoIdlingResource.increment()
                val sqlQuery = supportSQLiteQueryConverter.convert(queryParameters)
                val result = lcboItemDao.getLcboItems(sqlQuery)
                Timber.tag("Idling").d("Decrement - load from db")
                EspressoIdlingResource.decrement()
                return result
            }

            override suspend fun saveNetworkResult(item: List<LCBOItem>) {
                Timber.tag("Idling").d("Increment -  saveNetworkResult")
                EspressoIdlingResource.increment()
                lcboItemDao.insert(item)
                Timber.tag("Idling").d("Decrement -  saveNetworkResult")
                EspressoIdlingResource.decrement()
            }

            override fun shouldFetch(data: List<LCBOItem>?): Boolean {
                return true
            }

            override suspend fun fetchFromNetwork(): ApiResponse<List<LCBOItem>> {
                Timber.tag("Idling").d("Increment - fetchFromNetwork")
                EspressoIdlingResource.increment()
                val options = optionsMapConverter.convert(queryParameters)
                val result = lcboApiService.fetchLcboItems(options)
                Timber.tag("Idling").d("Decrement - fetchFromNetwork")
                EspressoIdlingResource.decrement()
                return result
            }

        }.asFlow()
    }
}