package com.ftech.dev.android_my_food.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ftech.dev.android_my_food.DetailFoodViewModel
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import meow.bottomnavigation.MeowBottomNavigation

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding
    lateinit var detailFoodViewModel: DetailFoodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        detailFoodViewModel = ViewModelProvider(this).get(DetailFoodViewModel::class.java)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment
        ) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)

//
//        binding.bottomNavigationMeow.add(MeowBottomNavigation.Model(1, R.drawable.ic_home))
//        binding.bottomNavigationMeow.add(MeowBottomNavigation.Model(2, R.drawable.ic_market))
//        binding.bottomNavigationMeow.add(MeowBottomNavigation.Model(3, R.drawable.ic_search))
//        binding.bottomNavigationMeow.add(MeowBottomNavigation.Model(4, R.drawable.ic_profile))


    }
}