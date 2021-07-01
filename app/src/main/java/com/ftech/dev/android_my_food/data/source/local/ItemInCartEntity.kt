package com.ftech.dev.android_my_food.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_itemincart")
class ItemInCartEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    @ColumnInfo(name = "nameItem")
    val nameItem:String,
    @ColumnInfo(name = "amount")
    val amount:Int,
    @ColumnInfo(name = "total")
    val total:Int
) {

}