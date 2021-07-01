package com.ftech.dev.android_my_food.ui.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ftech.dev.android_my_food.data.model.Food
import com.ftech.dev.android_my_food.data.model.Voucher
import com.ftech.dev.android_my_food.data.repository.ItemInCartRepository
import com.ftech.dev.android_my_food.data.source.local.ItemInCartEntity
import com.ftech.dev.android_my_food.data.source.local.SearchEntity

class CartViewModel : ViewModel() {

    private val itemInCartRepository = ItemInCartRepository()

    val liveItemInCart = MutableLiveData<ItemInCartEntity>()
    val listItemInCartLiveData = itemInCartRepository.getAllItemInCartLiveData()

    var amount = MutableLiveData<Int>(0)

//    private fun temp(): Int{
//        var temp = 0
//        for (item in listItemInCartLiveData.value!!){
//            temp+= item.amount*item.total
//        }
//        return temp
//    }

    var total = MutableLiveData<Int>(0)
    var promo = MutableLiveData<Int>(0)

    fun upAmount(v: Int = 1) {
        amount.value = liveItemInCart.value?.amount?.let { amount.value?.plus(it) }
        total.value = liveItemInCart.value?.total?.let { total.value?.plus(it) }
    }

    fun downAmount(v: Int = 1) {
        if (amount.value!! > 0) {
            amount.value = liveItemInCart.value?.amount?.let { amount.value?.minus(it) }
            total.value = liveItemInCart.value?.total?.let { total.value?.minus(it) }
        }
    }

    fun addPromo(voucher: Voucher){

    }

    fun deleteAll(){
        amount.value = 0
        total.value = 0
        itemInCartRepository.deleteAll()
    }

    fun insert(itemInCartEntity: ItemInCartEntity) {
        itemInCartRepository.insert(itemInCartEntity)
    }

    fun delete(itemInCartEntity: ItemInCartEntity) {
        itemInCartRepository.delete(itemInCartEntity)
    }
}