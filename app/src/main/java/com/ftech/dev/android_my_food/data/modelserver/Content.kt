package com.ftech.dev.android_my_food.data.modelserver

import com.ftech.dev.android_my_food.data.model.Food
import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("datalist")
    val datalist: MutableList<Food>
)