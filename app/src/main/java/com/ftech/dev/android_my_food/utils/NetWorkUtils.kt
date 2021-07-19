package com.ftech.dev.android_my_food.utils

import android.util.Log
import com.ftech.dev.android_my_food.data.model.Food
import com.ftech.dev.android_my_food.data.source.remote.APIService
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetWorkUtils {

    companion object {
        private const val BASE_URL = "https://nodejs-my-food.herokuapp.com/"

        private var retrofit: Retrofit? = null

        fun getClient(baseUrl: String?): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

        fun getService() : APIService{
            return getClient(BASE_URL)?.create(APIService::class.java)!!
        }

    }


}