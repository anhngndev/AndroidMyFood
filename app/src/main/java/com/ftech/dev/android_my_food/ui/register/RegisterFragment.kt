package com.ftech.dev.android_my_food.ui.register

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.data.model.User
import com.ftech.dev.android_my_food.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    private val TAG = "RegisterFragment"
    private val firebaseAuth = FirebaseAuth.getInstance()
    private lateinit var reference : DatabaseReference
    private var database= FirebaseDatabase.getInstance()

    override fun isCanBackPress() = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
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

        binding.ivBack.setOnClickListener {
            onBackPress()
        }

    }

    fun register() {
        val mail = binding.edtMail.text
        val pass = binding.edtPass.text
        val name = binding.edtName.text
        val phone = binding.edtPhone.text
        val passAgain = binding.edtPassAgain.text

        if (mail.toString().equals("")) {
            binding.edtMail.error = "Type"
        }
        if (name.toString().equals("")) {
            binding.edtName.error = "Type"
        }
        if (phone.toString().equals("")) {
            binding.edtPhone.error = "Type"
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
                        reference = database.getReference("users")
//                        database = FirebaseDatabase.getInstance().getReference("users")
                        val user = User(name.toString(), mail.toString(), phone.toString())
                        reference.child(name.toString()).setValue(user)

                        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE) ?: return@addOnCompleteListener
                        with (sharedPref.edit()) {
                            putString(mail.toString()+"name", name.toString())
                            putString(mail.toString()+"sdt", phone.toString())
                            apply()
                        }
                        Toast.makeText(context, "Sign up success!", Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack(R.id.loginFragment, false)

                    }
                }.addOnFailureListener {
                    Toast.makeText(context, "Sign up failed!", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun checkInput(): Boolean {
        if (binding.edtMail.text.toString().equals("") ||
            binding.edtPass.text.toString().equals("") ||
            binding.edtPhone.text.toString().equals("") ||
            binding.edtName.text.toString().equals("") ||
            !binding.edtPass.text.toString().equals(binding.edtPassAgain.text.toString())
        ) return false
        return true
    }
}