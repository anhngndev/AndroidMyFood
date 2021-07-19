package com.ftech.dev.android_my_food.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    var userNameLiveData = MutableLiveData<String>("")
    var userEmailLiveData = MutableLiveData<String>("")
    var userPassLiveData = MutableLiveData<String>("")
    var userPassAgainLiveData = MutableLiveData<String>("")
    val userPhoneNumberLivaData = MutableLiveData<String>("")

    fun isEmailValid(): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(userEmailLiveData.value).matches()
    }

    fun isPassValid(): Boolean {
        return (userPassLiveData.value!!.length > 5)
    }

    fun isNameValid(): Boolean {
        return (userNameLiveData.value!!.length > 5)
    }

    fun isPhoneValid(): Boolean {
        return (userPhoneNumberLivaData.value!!.length == 10)
    }

    fun isPassValidCorrect(): Boolean {
        return (userPassLiveData.value.equals(userPassAgainLiveData.value!!))
    }

    fun isInforValid(): Boolean {
        return (isEmailValid() && isPassValid() && isPassValidCorrect() && isPhoneValid() && isNameValid())
    }



}