package com.ftech.dev.android_my_food.shareviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class UserInforViewModel : ViewModel() {

    val userLiveData = MutableLiveData<FirebaseUser>()
    val userNameLivaData = MutableLiveData<String>()
    val userPhoneNumberLivaData = MutableLiveData<String>()

    var userEmailLiveData = MutableLiveData<String>("")
    var userPassLiveData = MutableLiveData<String>("")

    fun isEmailValid(): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(userEmailLiveData.value).matches()
    }

    fun isPassValid(): Boolean {
        return (userPassLiveData.value!!.length > 5)
    }

}