package com.ftech.dev.android_my_food.ui.register

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth


class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    private var firebaseAuth = FirebaseAuth.getInstance()

    override fun isCanBackPress() = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_register
    }

    override fun initView() {

    }

    override fun setAction() {
        binding.cvRegister.setOnClickListener {
            register()
        }

    }

    fun register() {
        val mail = binding.edtMail.text
        val pass = binding.edtPass.text
        val passAgain = binding.edtPassAgain.text

        if (mail.toString().equals("")) {
            binding.edtMail.error = "Type"
        }
        if (pass.toString().equals("")) {
            binding.edtPass.error = "Type"
        }
        if (!pass.toString().equals(passAgain.toString())) {
            binding.edtPassAgain.error = "Incorrect password"
        }

        if (checkInput()) {
            firebaseAuth.createUserWithEmailAndPassword(mail.toString(), pass.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(context, "Sign up success!", Toast.LENGTH_SHORT).show()

                    }
                }.addOnFailureListener {
                    Toast.makeText(context, "Sign up failed!", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun checkInput(): Boolean {
        if (binding.edtMail.text.toString().equals("") || binding.edtPass.text.toString()
                .equals("") || !binding.edtPass.text.toString()
                .equals(binding.edtPassAgain.text.toString())
        ) return false
        return true
    }
}