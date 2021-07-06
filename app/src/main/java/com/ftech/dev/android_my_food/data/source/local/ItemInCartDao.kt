package com.ftech.dev.android_my_food.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
@Dao
interface ItemInCartDao {
    @Insert()
    fun insert(itemInCartEntity: ItemInCartEntity)

    @Query("SELECT *FROM tbl_itemincart")
    fun getAllItemInCart(): List<ItemInCartEntity>

     @Query("SELECT *FROM tbl_itemincart where nameItem =:name")
    fun getItemInCartByName(name:String): ItemInCartEntity

    @Query("SELECT EXISTS(SELECT * FROM tbl_itemincart WHERE nameItem = :name)")
    fun isFoodIsExist(name:String) : Boolean

    @Delete
    fun delete(itemInCartEntity: ItemInCartEntity)

    @Query("DELETE FROM tbl_itemincart")
    fun deleteAll()

    @Query("SELECT *FROM tbl_itemincart order by id + 0 desc")
    fun getAllItemInCartLiveData(): LiveData<MutableList<ItemInCartEntity>>


}