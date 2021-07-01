package com.ftech.dev.android_my_food.data.repository

import android.util.Log
import com.ftech.dev.android_my_food.data.model.Food
import com.ftech.dev.android_my_food.data.modelserver.DataResponse
import com.ftech.dev.android_my_food.utils.NetWorkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataResponseRepository {
    private val TAG = "DataRepository"

    private val apiService = NetWorkUtils.getService()

    fun getFoodList(
        onSuccess:(foods: MutableList<Food>)-> Unit,
        onError: (t: Throwable?)-> Unit
    ) {
        apiService.getFoodList().enqueue(object : Callback<DataResponse> {
            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                if (response.isSuccessful){
                    onSuccess.invoke(response.body()?.content?.datalist!!)
                } else {
                    onError.invoke(null)
                }
            }
            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                onError.invoke(t)
            }
        })
    }
}

