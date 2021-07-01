package com.ftech.dev.android_my_food.data.model

import com.google.gson.annotations.SerializedName


class Food(
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("price")
    var price: String,
    @SerializedName("image")
    var image: String
) {
    fun getPriceToInt() = price.toInt()
}