package com.ftech.dev.android_my_food.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_order")
class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    @ColumnInfo(name = "name")
    var name:String,
    @ColumnInfo(name = "total")
    var total:Int,
    @ColumnInfo(name = "paymentMethod")
    var paymentMethod:String,
    @ColumnInfo(name = "amount")
    var amount:Int,
    @ColumnInfo(name = "date")
    var date:String,
    @ColumnInfo(name = "status")
    var status:Boolean
) {

}