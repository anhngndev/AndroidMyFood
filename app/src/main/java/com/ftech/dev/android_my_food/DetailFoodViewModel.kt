package com.ftech.dev.android_my_food

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ftech.dev.android_my_food.data.model.Food

class DetailFoodViewModel : ViewModel() {

//    private var savedStateHandle: SavedStateHandle  = SavedStateHandle()

     val liveFood = MutableLiveData<Food>()



}