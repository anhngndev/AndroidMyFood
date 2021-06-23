package com.ftech.dev.android_my_food.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SearchDao {
    @Insert()
    fun insert(searchEntity: SearchEntity)

    @Query("SELECT *FROM tbl_search")
    fun getRecentSearch(): List<SearchEntity>

    @Query("SELECT *FROM tbl_search order by id + 0 desc limit 5")
    fun getRecentSearchLiveData(): LiveData<List<SearchEntity>>
}