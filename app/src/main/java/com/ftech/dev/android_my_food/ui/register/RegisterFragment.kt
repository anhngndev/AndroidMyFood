package com.ftech.dev.android_my_food.ui.register

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.data.model.User
import com.ftech.dev.android_my_food.databinding.FragmentRegisterBinding
import com.ftech.dev.android_my_food.utils.observer
import com.ftech.dev.android_my_food.utils.onDebouncedClick
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    private val TAG = "RegisterFragment"
    private val firebaseAuth = FirebaseAuth.getInstance()
    private lateinit var reference: DatabaseReference
    private var database = FirebaseDatabase.getInstance()
    private val registerViewModel: RegisterViewModel by activityViewModels()

    override fun isCanBackPress() = true

    override fun getLayoutId(): Int {
        return R.layout.fragment_register
    }

    override fun initBinding() {
        binding.viewModel = registerViewModel
    }

    override fun initView() {
        observer(registerViewModel.userEmailLiveData) {
            if (!registerViewModel.isEmailValid()) {
                binding.edtMail.error = "Malformed"
            }
        }
        observer(registerViewModel.userNameLiveData) {
            if (!registerViewModel.isNameValid()) {
                binding.edtName.error = "More than 6 letter"
            }

            {

            }
        }

        observer(registerViewModel.userPhoneNumberLivaData) {
            if (!registerViewModel.isPhoneValid()) {
                binding.edtPhone.error = "Malformed"
            }
        }
        observer(registerViewModel.userPassLiveData) {
            if (!registerViewModel.isPassValid()) {
                binding.edtPass.error = "Pass more than 6 letter"
            }
        }
        observer(registerViewModel.userPassAgainLiveData) {
            if (!registerViewModel.isPassValidCorrect()) {
                binding.edtPassAgain.error = "Pass incorrect"
            }
        }
    }

    override fun setAction() {
        binding.cvRegister.onDebouncedClick {
            if (registerViewModel.isInforValid()) {
                register()
            }
        }

        binding.ivBack.onDebouncedClick {
            onBackPress()
        }

    }

    fun register() {
        val mail = binding.edtMail.text
        val pass = binding.edtPass.text
        val name = binding.edtName.text
        val phone = binding.edtPhone.text
        val passAgain = binding.edtPassAgain.text

        firebaseAuth.createUserWithEmailAndPassword(mail.toString(), pass.toString())
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    reference = database.getReference("users")
                    val user = User(name.toString(), mail.toString(), phone.toString())
                    reference.child(name.toString()).setValue(user)

                    val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
                        ?: return@addOnCompleteListener
                    with(sharedPref.edit()) {
                        putString(mail.toString() + "name", name.toString())
                        putString(mail.toString() + "sdt", phone.toString())
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