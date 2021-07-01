package com.ftech.dev.android_my_food.ui.profile

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.UserInforViewModel
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.databinding.FragmentProfileBinding
import com.ftech.dev.android_my_food.ui.main.MainViewModel
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private val userInforViewModel : UserInforViewModel by activityViewModels()

    private var firebaseAuth = FirebaseAuth.getInstance()
    override fun isCanBackPress() = true
    override fun getLayoutId(): Int {
        return R.layout.fragment_profile
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun initView() {
        binding.userInforViewModel = userInforViewModel
    }

    override fun setAction() {
        binding.ivBack.setOnClickListener {
            onBackPress()
        }

        binding.tvProfile.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
        }

        binding.tvMyOrder.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_historyOrderFragment)
        }

        binding.tvSetting.setOnClickListener { }

        binding.tvLanguage.setOnClickListener { }

        binding.tvLogOut.setOnClickListener {
            val builder =
                AlertDialog.Builder(requireContext())
            builder.setTitle("Foody")
            builder.setMessage("Do you want to log out?")
                .setCancelable(false)
                .setPositiveButton(
                    "Yes"
                ) { dialog, id ->
                    firebaseAuth.signOut()
                    findNavController().popBackStack(R.id.loginFragment, false)
                }
                .setNegativeButton(
                    "No"
                ) { dialog, id -> dialog.cancel() }
            val alert = builder.create()
            alert.show()
        }
    }


}