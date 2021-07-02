package com.ftech.dev.android_my_food.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OrderDao {
    @Insert
    fun insert(orderEntity: OrderEntity)

    @Query("SELECT *FROM tbl_order order by id + 0 desc")
    fun getAllLiveDataOrders(): LiveData<MutableList<OrderEntity>>
}