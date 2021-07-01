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

//        @JvmStatic
//        fun jsonToFoodList(json: String?): MutableList<Food> {
//            val list: MutableList<Food> = mutableListOf()
//            try {
//                val jsonObject = JSONObject(json)
//
//                val content = jsonObject.getJSONObject("content")
//                val message = jsonObject.getString("message")
//                val status = jsonObject.getInt("status")
//
//                val foodList = content.getJSONArray("datalist")
//                val TAG = "NetWorkUtils"
//                Log.d(TAG, "jsonToFoodList: ${foodList}")
//                for (i in 0 until foodList.length()) {
//                    val `object` = foodList.getJSONObject(i)
//                    val id = `object`.getString("id")
//                    val name = `object`.getString("name")
//                    val image = `object`.getString("image")
//                    val price = `object`.getInt("price")
//                    val food = Food(id, name, price, image)
//                    list.add(food)
//                }
//            } catch (e: JSONException) {
//                e.printStackTrace()
//            }
//            return list
//        }

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