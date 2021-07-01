package com.ftech.dev.android_my_food.data.source.remote

import com.ftech.dev.android_my_food.data.modelserver.DataResponse
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("/food")
    fun getFoodList() : Call<DataResponse>
}