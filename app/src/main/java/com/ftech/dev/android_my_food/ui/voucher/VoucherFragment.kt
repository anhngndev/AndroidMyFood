package com.ftech.dev.android_my_food.ui.voucher

import androidx.fragment.app.activityViewModels
import com.ftech.dev.android_my_food.shareviewmodel.FoodViewModel
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.databinding.FragmentVoucherBinding
import com.ftech.dev.android_my_food.utils.onDebouncedClick


class VoucherFragment : BaseFragment<FragmentVoucherBinding>(){

    private val foodlViewModel: FoodViewModel by activityViewModels()

    override fun isCanBackPress() = true

    override fun getLayoutId() = R.layout.fragment_voucher

    override fun initView() {
        binding.item = foodlViewModel.liveVoucher.value
    }

    override fun setAction() {
        binding.ivBack.onDebouncedClick {
            onBackPress()
        }
    }

}