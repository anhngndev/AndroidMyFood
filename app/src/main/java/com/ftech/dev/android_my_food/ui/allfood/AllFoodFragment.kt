package com.ftech.dev.android_my_food.ui.allfood

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
import com.ftech.dev.android_my_food.data.model.Food
import com.ftech.dev.android_my_food.databinding.FragmentAllFoodBinding
import com.ftech.dev.android_my_food.ui.home.FoodAdapterVer
import com.ftech.dev.android_my_food.utils.observer

class AllFoodFragment : BaseFragment<FragmentAllFoodBinding>(), FoodAdapterVer.FoodListener {

    private val foodsViewModel: FoodDetailViewModel by activityViewModels()
    private var foodAdapter = FoodAdapterVer()

    override fun isCanBackPress() = true

    override fun getLayoutId() = R.layout.fragment_all_food

    override fun initView() {
        foodAdapter.list = foodsViewModel.foodsLiveData.value!!
        foodAdapter.callBack = this
        binding.rcFood.adapter = foodAdapter

        observer(foodsViewModel.foodsLiveData) {
            it?.let { list ->
                foodAdapter.list = list
            }
        }
    }

    override fun setAction() {
        binding.ivBack.setOnClickListener {
            onBackPress()
        }
    }

    override fun onItemClick(index: Int, item: Food) {
        foodsViewModel.liveFood.value = (item)
        foodsViewModel.amount.value = 1
        foodsViewModel.total.value = item.getPriceToInt()
        findNavController().navigate(R.id.action_allFoodFragment_to_detailFoodBottomSheet)
    }


}