package com.ftech.dev.android_my_food.ui.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.databinding.FragmentLoginBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import meow.bottomnavigation.MeowBottomNavigation


class LoginFragment : BaseFragment<FragmentLoginBinding>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAction()
    }

    override fun setAction() {

        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation)
        navBar.visibility = View.GONE

        binding.layoutRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.ivLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }

    override fun initView() {

    }


}