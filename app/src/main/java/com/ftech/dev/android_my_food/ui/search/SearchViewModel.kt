package com.ftech.dev.android_my_food.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ftech.dev.android_my_food.data.repository.SearchRepository
import com.ftech.dev.android_my_food.data.source.local.SearchEntity

class SearchViewModel : ViewModel() {

    private val repository = SearchRepository()
    val listSearchLiveData = MutableLiveData<List<SearchEntity>>()
    val listSearchLiveData2 = repository.getRecentSearchLiveData()

    fun insert(searchEntity: SearchEntity) {
        repository.insert(searchEntity)
    }

    fun getRecentSearch() {
        listSearchLiveData.value = repository.getRecentSearch()
    }


}