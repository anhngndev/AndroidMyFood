package com.ftech.dev.android_my_food

import android.app.Application
import com.ftech.dev.android_my_food.utils.initApplication

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initApplication()
    }

}