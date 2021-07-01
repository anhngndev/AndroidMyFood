package com.ftech.dev.android_my_food.data.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    val name:String? = null,
    val mail:String? = null,
    val phone:String? = null
) {
}