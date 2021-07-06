package com.ftech.dev.android_my_food.ui.orderdetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ftech.dev.android_my_food.FoodDetailViewModel
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.data.source.local.ItemInCartEntity
import com.ftech.dev.android_my_food.databinding.FragmentOrderDetailBinding
import com.ftech.dev.android_my_food.ui.cart.CartViewModel

class OderDetailFragment : BaseFragment<FragmentOrderDetailBinding>() {

    private val TAG = "OderDetailFragment"
    private val foodDetailViewModel: FoodDetailViewModel by activityViewModels()
    private val cartViewModel : CartViewModel by activityViewModels()

    override fun isCanBackPress() = true

    override fun initBinding() {
        binding.viewModel = foodDetailViewModel
        binding.food = foodDetailViewModel.liveFood.value
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_order_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun setAction() {
        binding.imvZoomIn.setOnClickListener {
            foodDetailViewModel.upAmount()
        }

        binding.imvZoomOut.setOnClickListener {
            foodDetailViewModel.downAmount()
        }

        binding.tvCheckout.setOnClickListener {
            if(foodDetailViewModel.amount.value!! > 0) {
                val itemInCartEntity = ItemInCartEntity(
                    nameItem = binding.food!!.name,
                    amount = foodDetailViewModel.amount.value!!.toInt(),
                    total = foodDetailViewModel.total.value!!.toInt()
                )
                cartViewModel.insert(itemInCartEntity)

                cartViewModel.liveItemInCart.value = itemInCartEntity

                cartViewModel.upAmount()
                cartViewModel.listItemInCartLiveData.value?.add(itemInCartEntity)
                foodDetailViewModel.resetAmount()
                findNavController().navigate(R.id.action_oderDetailFragment_to_cartFragment)
            }
        }

        binding.ivBack.setOnClickListener {
            onBackPress()
        }

    }

    override fun initView() {

    }

    override fun onDestroy() {
        super.onDestroy()
        foodDetailViewModel.resetAmount()
    }

}

