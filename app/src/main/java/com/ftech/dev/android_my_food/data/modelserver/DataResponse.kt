package com.ftech.dev.android_my_food.data.modelserver

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("content")
    val content: Content,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)