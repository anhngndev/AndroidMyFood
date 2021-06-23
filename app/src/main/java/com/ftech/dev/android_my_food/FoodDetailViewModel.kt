package com.ftech.dev.android_my_food

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ftech.dev.android_my_food.data.model.Food
import com.ftech.dev.android_my_food.data.model.FoodBig

class FoodDetailViewModel : ViewModel() {

    val liveFood = MutableLiveData<Food>()
    val liveBigFood = MutableLiveData<FoodBig>()

    var amount = MutableLiveData<Int>(0)
    var total = MutableLiveData<Int>(0)

    fun upAmount(v: Int = 1) {
        amount.value = amount.value?.plus(v)
        total.value = liveFood.value?.price?.let { total.value?.plus(it) }
    }

    fun downAmount(v: Int = 1) {
        if (amount.value!! > 0) {
            amount.value = amount.value?.minus(v)
            total.value = liveFood.value?.price?.let { total.value?.minus(it) }
        }
    }

    fun resetAmount() {
        amount.value = 1
        total.value = liveFood.value?.price
    }
}


