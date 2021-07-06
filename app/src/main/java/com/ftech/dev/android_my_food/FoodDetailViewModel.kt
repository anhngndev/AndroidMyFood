package com.ftech.dev.android_my_food

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.ftech.dev.android_my_food.data.model.Food
import com.ftech.dev.android_my_food.data.model.BigFood
import com.ftech.dev.android_my_food.data.model.Voucher
import com.ftech.dev.android_my_food.data.repository.DataResponseRepository
import com.ftech.dev.android_my_food.data.source.local.ItemInCartEntity

class FoodDetailViewModel : ViewModel() {

    private val TAG = "FoodDetailViewModel"
    private val dataResponseRepository = DataResponseRepository()

    val foodsLiveData = MutableLiveData<MutableList<Food>>()

    val tempFoodsLiveData = MutableLiveData<MutableList<Food>>()
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
        val temp  = mutableListOf<Food>()
        dataResponseRepository.getFoodList(onSuccess = {
            foodsLiveData.value = it
            it.forEachIndexed { index, food ->
                if (index < 10) {
                    temp.add(food)
                }
            }
            tempFoodsLiveData.value = temp.toMutableList()
        }, onError = {
            Log.d(TAG, "getFoods: ${it}")
        })
    }

//    fun getTempFoods(amount: Int) {
//        if (tempAmountFood.value == 0){
//            if (foodsLiveData.value?.size!! > amount){
//                tempAmountFood.value = amount
//                for(i in 0 until amount - 1){
//                    tempFoodsLiveData.value?.add(foodsLiveData.value!![i])
//                    Log.d(TAG, "getTempFoods 0: ${tempFoodsLiveData.value?.size}")
//
//                }
//                Log.d(TAG, "getTempFoods 1: ${tempFoodsLiveData.value?.size}")
//
//            } else{
//                tempAmountFood.value = foodsLiveData.value?.size
//                tempFoodsLiveData.value = foodsLiveData.value!!
//                Log.d(TAG, "getTempFoods 2: ${tempFoodsLiveData.value?.size}")
//
//            }
//        }else{
//
//            if ((tempAmountFood?.value!! + amount) > foodsLiveData.value?.size!! ){
//                tempAmountFood.value = foodsLiveData.value?.size
//                tempFoodsLiveData.value = foodsLiveData.value!!
//                Log.d(TAG, "getTempFoods 3: ${tempFoodsLiveData.value?.size}")
//
//            } else{
//                for(i in tempAmountFood.value!! until tempAmountFood.value!! + amount - 1){
//                    tempFoodsLiveData.value?.add(foodsLiveData.value!![i])
//
//                }
//                Log.d(TAG, "getTempFoods 4: ${tempFoodsLiveData.value?.size}")
//
//            }
//        }
//        Log.d(TAG, "getTempFoods: ${tempFoodsLiveData.value?.size}")
//
//    }



}


