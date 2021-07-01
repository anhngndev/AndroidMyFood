package com.ftech.dev.android_my_food.ui.profile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.UserInforViewModel
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.databinding.FragmentEditProfileBinding
import com.ftech.dev.android_my_food.ui.main.MainViewModel


class EditProfileFragment : BaseFragment<FragmentEditProfileBinding>() {

    private val userInforViewModel by activityViewModels<UserInforViewModel>()

    override fun isCanBackPress() = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun getLayoutId() = R.layout.fragment_edit_profile

    override fun initView() {
        binding.userViewModel = userInforViewModel
    }

    override fun setAction() {
        binding.ivBack.setOnClickListener {
            onBackPress()
        }

        binding.tvUpdate.setOnClickListener {
            val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE) ?: return@setOnClickListener
            with (sharedPref.edit()) {
                putString(userInforViewModel.userLiveData.value?.email+"name", binding.edtName.text.toString())
                userInforViewModel.userNameLivaData.value = binding.edtName.text.toString()
                apply()
            }
            onBackPress()
        }

        binding.tvOut.setOnClickListener {
            onBackPress()
        }

        binding.isFocusName = binding.edtName.isFocusable
        binding.isFocusMail = binding.edtMail.isFocusable
        binding.isFocusPass = binding.edtPass.isFocusable


    }

}