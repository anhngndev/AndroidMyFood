package com.ftech.dev.android_my_food

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class UserInforViewModel : ViewModel() {

    val userLiveData = MutableLiveData<FirebaseUser>()
    val userNameLivaData = MutableLiveData<String>()
    val userPhoneNumberLivaData = MutableLiveData<String>()

}