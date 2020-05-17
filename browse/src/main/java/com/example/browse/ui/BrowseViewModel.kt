package com.example.browse.ui

import androidx.lifecycle.*
import com.example.browse.repo.BrowseRepository
import com.example.core.ui.events.Event
import com.example.core.vo.LCBOItem
import com.example.core.vo.LCBOItemQueryParameters
import com.example.core.vo.Resource
import javax.inject.Inject

class BrowseViewModel @Inject constructor(
    val browseRepository: BrowseRepository
) : ViewModel() {

    val queryParameters = LCBOItemQueryParameters()

    private val _search = MutableLiveData<Event<Any>>()
    fun search() {
        _search.value = Event(Unit)
    }

    private var _searchResults = Transformations.switchMap(_search) {
        browseRepository.search(queryParameters).asLiveData()
    }
    val searchResults: LiveData<Resource<List<LCBOItem>>>
        get() = _searchResults

}