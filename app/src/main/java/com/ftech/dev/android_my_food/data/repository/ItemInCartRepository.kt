package com.ftech.dev.android_my_food.data.repository

import androidx.lifecycle.LiveData
import com.ftech.dev.android_my_food.data.source.local.AppDatabase
import com.ftech.dev.android_my_food.data.source.local.ItemInCartEntity
import com.ftech.dev.android_my_food.data.source.local.SearchEntity

class ItemInCartRepository {
    private val database = AppDatabase.getInstance()
    private val itemInCartDao = database.itemInCartDao()

    fun insert(itemInCartEntity: ItemInCartEntity) {
        itemInCartDao.insert(itemInCartEntity)
    }

    fun deleteAll(){
        itemInCartDao.deleteAll()
    }

    fun delete(itemInCartEntity: ItemInCartEntity) {
        itemInCartDao.delete(itemInCartEntity)
    }

    fun getAllItemInCartLiveData(): LiveData<MutableList<ItemInCartEntity>> {
        return itemInCartDao.getAllItemInCartLiveData()
    }
}