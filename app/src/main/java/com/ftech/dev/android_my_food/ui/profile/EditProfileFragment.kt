package com.ftech.dev.android_my_food.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.databinding.FragmentEditProfileBinding
import com.ftech.dev.android_my_food.ui.main.MainViewModel


class EditProfileFragment : BaseFragment<FragmentEditProfileBinding>() {

    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun isCanBackPress() = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun getLayoutId() = R.layout.fragment_edit_profile

    override fun initView() {
        mainViewModel.stateNavigationBotstom.value = false

    }

    override fun setAction() {
        binding.ivBack.setOnClickListener {
            onBackPress()
        }

        binding.tvUpdate.setOnClickListener {
            onBackPress()
        }
    }

}