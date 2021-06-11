package com.ftech.dev.android_my_food.ui.detailfood

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.databinding.FragmentDetailFoodBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetailFoodFragment  : BaseFragment<FragmentDetailFoodBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_detail_food
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAction()
    }

    private fun setAction() {

    }
}