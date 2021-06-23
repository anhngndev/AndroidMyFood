package com.ftech.dev.android_my_food.ui.splash

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.databinding.FragmentSplashBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import meow.bottomnavigation.MeowBottomNavigation

class SplashFragment: BaseFragment<FragmentSplashBinding>(){

    companion object {
        private const val TAG = "SplashFragment"
    }

    override fun getLayoutId(): Int {
       return R.layout.fragment_splash
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun setAction() {
        binding.layout.setOnClickListener {
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }

    override fun initView() {
        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation)
        navBar.visibility = View.GONE
    }



}

