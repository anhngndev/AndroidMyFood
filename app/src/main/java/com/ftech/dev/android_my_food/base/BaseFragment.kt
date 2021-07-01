package com.ftech.dev.android_my_food.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ftech.dev.android_my_food.ui.main.MainViewModel

abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {

//    private val mainViewModel by activityViewModels<MainViewModel>()

    protected lateinit var binding: DB

    abstract fun getLayoutId(): Int
    open fun initBinding() {}
    abstract fun initView()
    abstract fun setAction()
    open fun isCanBackPress() = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        initBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isCanBackPress()) {
            setDispatcherOnBackPress()
        }
        initView()
        setAction()
    }

    private fun setDispatcherOnBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
        }
    }

    open fun onBackPress() {
        requireActivity().onBackPressed()
    }

    open fun onFinish(){
    }
}
