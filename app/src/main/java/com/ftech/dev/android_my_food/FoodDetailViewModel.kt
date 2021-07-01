package com.ftech.dev.android_my_food

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ftech.dev.android_my_food.data.model.Food
import com.ftech.dev.android_my_food.data.model.BigFood
import com.ftech.dev.android_my_food.data.model.Voucher
import com.ftech.dev.android_my_food.data.repository.DataResponseRepository

class FoodDetailViewModel : ViewModel() {

    private val TAG = "FoodDetailViewModel"
    private val dataResponseRepository = DataResponseRepository()
    val foodsLiveData = MutableLiveData<MutableList<Food>>()

    val liveFood = MutableLiveData<Food>()
    val liveBigFood = MutableLiveData<BigFood>()
    val liveVoucher = MutableLiveData<Voucher>()

    var amount = MutableLiveData<Int>(0)
    var total = MutableLiveData<Int>(0)

    fun upAmount(v: Int = 1) {
        amount.value = amount.value?.plus(v)
        total.value = liveFood.value?.getPriceToInt()?.let { total.value?.plus(it) }
    }

    fun downAmount(v: Int = 1) {
        if (amount.value!! > 0) {
            amount.value = amount.value?.minus(v)
            total.value = liveFood.value?.getPriceToInt()?.let { total.value?.minus(it) }
        }
    }

    fun resetAmount() {
        amount.value = 1
        total.value = liveFood.value?.getPriceToInt()
    }

    fun getFoods() {
        dataResponseRepository.getFoodList(onSuccess = {
            foodsLiveData.value = it
        }, onError = {
            Log.d(TAG, "getFoods: ${it}")
        })
    }
}


