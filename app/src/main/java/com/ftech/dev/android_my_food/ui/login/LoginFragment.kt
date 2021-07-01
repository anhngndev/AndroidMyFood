package com.ftech.dev.android_my_food.ui.login

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.UserInforViewModel
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.databinding.FragmentLoginBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private var firebaseAuth = FirebaseAuth.getInstance()
    private val userViewModel : UserInforViewModel by activityViewModels()

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

        binding.layoutRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.tvLogin.setOnClickListener {
            login()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }

    override fun initView() {

    }

    private fun checkInput(): Boolean {
        if (binding.edtMail.text.toString().equals("") || binding.edtPass.text.toString()
                .equals("")
        ) return false
        return true
    }

    private fun login() {
        val mail = binding.edtMail.text
        val pass = binding.edtPass.text

        if (checkInput()) {
            firebaseAuth.signInWithEmailAndPassword(mail.toString(), pass.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return@addOnCompleteListener
                        val name = sharedPref.getString(mail.toString()+"name", "not found name")
                        val phone = sharedPref.getString(mail.toString()+"sdt", "not found phone number")
                        userViewModel.userLiveData.value = firebaseAuth.currentUser
                        userViewModel.userNameLivaData.value = name
                        userViewModel.userPhoneNumberLivaData.value = phone
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(context, "Login failed!", Toast.LENGTH_SHORT).show()
                }

        } else {
            if (mail.toString().equals("")) {
                binding.edtMail.error = "Type"
            }
            if (pass.toString().equals("")) {
                binding.edtPass.error = "Type"
            }
            Toast.makeText(context, "Please type full!", Toast.LENGTH_SHORT).show()
        }
    }


}