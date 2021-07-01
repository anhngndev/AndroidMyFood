package com.ftech.dev.android_my_food.ui.main

import android.annotation.SuppressLint
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.databinding.ActivityMainBinding
import com.ftech.dev.android_my_food.ui.cart.CartViewModel


class MainActivity : AppCompatActivity() {

    private val cartViewModel: CartViewModel by viewModels()
    private lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

//        createNotificationChannel()

//        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
//        cartViewModel.amount.value = sharedPref.getInt("amount", 0)
//        cartViewModel.total.value = sharedPref.getInt("total", 0)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment
        ) as NavHostFragment
        navController = navHostFragment.navController


//        NAVIGATION BOTTOM
//        binding.bottomNavigation.setupWithNavController(navController)
//        mainViewModel.stateNavigationBotstom.observe(this, { state ->
//            if (state) {
//                binding.bottomNavigation.visibility = View.VISIBLE
//            } else {
//                binding.bottomNavigation.visibility = View.GONE
//            }
//        })

    }

    override fun onDestroy() {
        super.onDestroy()
//        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
//        with (sharedPref.edit()) {
//            putInt("amount", cartViewModel.amount.value!!)
//            putInt("total", cartViewModel.total.value!!)
//            apply()
//        }
    }



    @SuppressLint("WrongConstant")
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.app_name)
            val descriptionText = getString(R.string.app_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("1", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService("1") as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}