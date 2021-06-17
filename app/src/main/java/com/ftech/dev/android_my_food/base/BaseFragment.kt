package com.ftech.dev.android_my_food.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.ftech.dev.android_my_food.ui.main.MainViewModel

abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {

    private val mainViewModel by activityViewModels<MainViewModel>()

    protected lateinit var binding: DB

    abstract fun getLayoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,getLayoutId() , container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    fun setStateBottomNavigation(value: Boolean){
        mainViewModel.stateNavigationBotstom.value = value
    }


}