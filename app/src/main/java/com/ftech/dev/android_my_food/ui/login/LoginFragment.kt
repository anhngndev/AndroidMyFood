package com.ftech.dev.android_my_food.ui.login

import android.content.Context
import android.os.Handler
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.shareviewmodel.UserInforViewModel
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.databinding.FragmentLoginBinding
import com.ftech.dev.android_my_food.ui.cart.CartViewModel
import com.ftech.dev.android_my_food.ui.main.MainViewModel
import com.ftech.dev.android_my_food.utils.observer
import com.ftech.dev.android_my_food.utils.onDebouncedClick
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val TAG = "LoginFragment"
    private var firebaseAuth = FirebaseAuth.getInstance()
    private val userViewModel: UserInforViewModel by activityViewModels()
    private val cartViewModel: CartViewModel by activityViewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private var handler = Handler()

    override fun initBinding() {

        binding.viewModel = userViewModel
    }


    override fun setAction() {

        binding.layoutRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.tvLogin.onDebouncedClick {
            if (userViewModel.isEmailValid() && userViewModel.isPassValid()) {
                login()
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }

    override fun initView() {
        observer(userViewModel.userEmailLiveData) {
            if (!userViewModel.isEmailValid()) {
                binding.edtMail.error = "Malformed"
            }
        }
        observer(userViewModel.userPassLiveData) {
            if (!userViewModel.isPassValid()) {
                binding.edtPass.error = "Pass more than 6 letter"
            }
        }

    }

    private fun checkInput(): Boolean {
        if (binding.edtMail.text.toString().equals("") || binding.edtPass.text.toString()
                .equals("")
        ) return false
        return true
    }

    private fun login() {
        mainViewModel.showLoading()

        val mail = binding.edtMail.text
        val pass = binding.edtPass.text

        if (checkInput()) {
            firebaseAuth.signInWithEmailAndPassword(mail.toString(), pass.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                            ?: return@addOnCompleteListener
                        val name = sharedPref.getString(mail.toString() + "name", "not found name")
                        val phone =
                            sharedPref.getString(mail.toString() + "sdt", "not found phone number")
                        userViewModel.userLiveData.value = firebaseAuth.currentUser
                        userViewModel.userNameLivaData.value = name
                        userViewModel.userPhoneNumberLivaData.value = phone
                        mainViewModel.hideLoading()
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                }
                .addOnFailureListener {
                    mainViewModel.hideLoading()
                    Toast.makeText(context, "Login failed!", Toast.LENGTH_SHORT).show()
                }

        } else {
            mainViewModel.hideLoading()
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