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

     var amount  = MutableLiveData<Int>(1)

     fun upAmount(v :Int = 1) {
          amount.value = amount.value?.plus(v)
     }

     fun downAmount(v :Int = 1) {
          amount.value = amount.value?.minus(v)
     }

     fun resetAmount(){
          amount.value = 1
     }
}


