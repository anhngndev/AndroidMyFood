package com.ftech.dev.android_my_food.ui.profile

import android.os.Bundle
import android.view.View
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_profile
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initView() {
    }

    override fun setAction() {
        binding.clEditProfile.setOnClickListener {  }
        binding.clSetting.setOnClickListener {  }
        binding.clLanguege.setOnClickListener {  }
        binding.clLogOut.setOnClickListener {  }
    }


}