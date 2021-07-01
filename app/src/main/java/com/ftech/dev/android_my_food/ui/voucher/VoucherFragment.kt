package com.ftech.dev.android_my_food.ui.voucher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ftech.dev.android_my_food.FoodDetailViewModel
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.data.model.Voucher
import com.ftech.dev.android_my_food.databinding.FragmentVoucherBinding
import com.ftech.dev.android_my_food.utils.DataFake


class VoucherFragment : BaseFragment<FragmentVoucherBinding>(){

    private val foodDetailViewModel: FoodDetailViewModel by activityViewModels()

    override fun isCanBackPress() = true

    override fun getLayoutId() = R.layout.fragment_voucher

    override fun initView() {
        binding.item = foodDetailViewModel.liveVoucher.value
    }

    override fun setAction() {
        binding.ivBack.setOnClickListener {
            onBackPress()
        }
    }

}