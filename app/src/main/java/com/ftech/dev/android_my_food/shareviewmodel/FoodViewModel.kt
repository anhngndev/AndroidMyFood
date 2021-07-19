package com.ftech.dev.android_my_food.shareviewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ftech.dev.android_my_food.data.model.Food
import com.ftech.dev.android_my_food.data.model.BigFood
import com.ftech.dev.android_my_food.data.model.Voucher
import com.ftech.dev.android_my_food.data.repository.DataResponseRepository
import kotlinx.coroutines.launch

class FoodViewModel : ViewModel() {


    private val TAG = "FoodDetailViewModel"
    private val dataResponseRepository = DataResponseRepository()

    val foodsLiveData = MutableLiveData<MutableList<Food>>()

    val currentFoodsLiveData = MutableLiveData<MutableList<Food>>()
    var tempAmountFood = MutableLiveData<Int>(0)

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
        if (amount.value!! > 1) {
            amount.value = amount.value?.minus(v)
            total.value = liveFood.value?.getPriceToInt()?.let { total.value?.minus(it) }
        }
    }

    fun resetAmount() {
        amount.value = 1
        total.value = liveFood.value?.getPriceToInt()
    }

    fun getFoods() {
        val temp = mutableListOf<Food>()
        dataResponseRepository.getFoodList(onSuccess = {
            foodsLiveData.value = it
            it.forEachIndexed { index, food ->
                if (index < 7) {
                    temp.add(food)
                    Log.d(TAG, "getFoods: $index")
                }
            }
            currentFoodsLiveData.value = temp.toMutableList()
        }, onError = {
            Log.d(TAG, "getFoods: ${it}")
        })
    }

    fun loadMore(value: Int) {
        val newList = currentFoodsLiveData.value!!
        val fullList = foodsLiveData.value!!
        val currSize = currentFoodsLiveData.value?.size!!
        var des = 0
        if(currSize != fullList.size) {
            des = if (currSize + value >= fullList.size) {
                fullList.size
            } else {
                currSize + value
            }
            for (i in currSize until des) {
                newList.add(fullList[i])
            }
            currentFoodsLiveData.value = newList
        }
    }

    fun resetCurrentFoods() {
        currentFoodsLiveData.value?.clear()
    }


}


