package com.ftech.dev.android_my_food.data.repository

import androidx.lifecycle.LiveData
import com.ftech.dev.android_my_food.data.source.local.AppDatabase
import com.ftech.dev.android_my_food.data.source.local.SearchDao
import com.ftech.dev.android_my_food.data.source.local.SearchEntity
import com.ftech.dev.android_my_food.utils.getApplication

class SearchRepository {

    private val database = AppDatabase.getInstance()
    private val searchDao = database.searchDao()

    fun insert(searchEntity: SearchEntity) {
        searchDao.insert(searchEntity)
    }

    fun getRecentSearch(): List<SearchEntity> {
        return searchDao.getRecentSearch()
    }

    fun getRecentSearchLiveData(): LiveData<List<SearchEntity>> {
        return searchDao.getRecentSearchLiveData()
    }

}