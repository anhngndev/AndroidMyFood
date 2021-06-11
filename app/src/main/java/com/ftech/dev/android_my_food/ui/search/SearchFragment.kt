package com.ftech.dev.android_my_food.ui.search

import android.os.Bundle
import android.view.View
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.databinding.FragmentSearchBinding

class SearchFragment :BaseFragment<FragmentSearchBinding>(){

    override fun getLayoutId(): Int {
        return R.layout.fragment_search
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
