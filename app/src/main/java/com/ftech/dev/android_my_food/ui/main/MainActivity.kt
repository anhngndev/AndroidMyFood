package com.ftech.dev.android_my_food.ui.main

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.databinding.ActivityMainBinding
import com.ftech.dev.android_my_food.ui.cart.CartViewModel


class MainActivity : AppCompatActivity() {

    private val cartViewModel: CartViewModel by viewModels()
    private val mainViewModel: MainViewModel by viewModels()
    private var handler = Handler()
    private lateinit var navController: NavController

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewModel = mainViewModel
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment
        ) as NavHostFragment
        navController = navHostFragment.navController

    }

    override fun onResume() {
        super.onResume()
        mainViewModel.stateLoading.observe(this) {
            if (it) {
                binding.view.visibility = View.VISIBLE
            } else {
                binding.view.visibility = View.GONE
            }
        }
    }

    override fun onStop() {
        super.onStop()
        cartViewModel.deleteAll()
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