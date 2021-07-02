package com.ftech.dev.android_my_food.data.repository

import androidx.lifecycle.LiveData
import com.ftech.dev.android_my_food.data.source.local.AppDatabase
import com.ftech.dev.android_my_food.data.source.local.OrderEntity
import com.ftech.dev.android_my_food.data.source.local.SearchEntity

class OrderRepository {
    private val database = AppDatabase.getInstance()
    private val orderDao = database.orderDao()

    fun insert(orderEntity: OrderEntity) {
        orderDao.insert(orderEntity)
    }

    fun getLiveDataOrders() = orderDao.getAllLiveDataOrders()

}