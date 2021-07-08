package com.ftech.dev.android_my_food.ui.allfood

import android.util.Log
import android.os.Handler

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ftech.dev.android_my_food.FoodDetailViewModel
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.data.model.Food
import com.ftech.dev.android_my_food.databinding.FragmentAllFoodBinding
import com.ftech.dev.android_my_food.ui.home.FoodAdapterVer
import com.ftech.dev.android_my_food.utils.observer


class AllFoodFragment : BaseFragment<FragmentAllFoodBinding>(), FoodAdapterVer.FoodListener {

    private val TAG = "AllFoodFragment"
    private val foodsViewModel: FoodDetailViewModel by activityViewModels()
    private var foodAdapter = FoodAdapterVer()
    private var handler = android.os.Handler()

    override fun isCanBackPress() = true

    override fun getLayoutId() = R.layout.fragment_all_food


    override fun initView() {
        observer(foodsViewModel.currentFoodsLiveData) {
            Log.d(TAG, "initView: ${it?.size}")
            it?.let { list ->
                binding.view.visibility = View.VISIBLE
                handler.postDelayed(Runnable {
                    binding.view.visibility = View.GONE
                    foodAdapter.list = list

                }, 1000)
            }
        }

        foodAdapter.callBack = this

        foodAdapter.loadMore = {
            foodsViewModel.loadMore(5)
        }

        binding.rcFood.adapter = foodAdapter

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
        findNavController().navigate(R.id.action_allFoodFragment_to_oderDetailFragment)
    }

    fun getTempFoods(amount: Int) {
        if (foodsViewModel.tempAmountFood.value == 0) {
            if (foodsViewModel.foodsLiveData.value?.size!! > amount) {
                foodsViewModel.tempAmountFood.value = amount
                for (i in 0 until amount - 1) {
                    foodsViewModel.currentFoodsLiveData.value?.add(foodsViewModel.foodsLiveData.value!![i])
                    Log.d(TAG, "getTempFoods 0: ${foodsViewModel.currentFoodsLiveData.value?.size}")

                }
                Log.d(TAG, "getTempFoods 1: ${foodsViewModel.currentFoodsLiveData.value?.size}")

            } else {
                foodsViewModel.tempAmountFood.value = foodsViewModel.foodsLiveData.value?.size
                foodsViewModel.currentFoodsLiveData.value = foodsViewModel.foodsLiveData.value!!
                Log.d(TAG, "getTempFoods 2: ${foodsViewModel.currentFoodsLiveData.value?.size}")

            }
        } else {

            if ((foodsViewModel.tempAmountFood?.value!! + amount) > foodsViewModel.foodsLiveData.value?.size!!) {
                foodsViewModel.tempAmountFood.value = foodsViewModel.foodsLiveData.value?.size
                foodsViewModel.currentFoodsLiveData.value = foodsViewModel.foodsLiveData.value!!
                Log.d(TAG, "getTempFoods 3: ${foodsViewModel.currentFoodsLiveData.value?.size}")

            } else {
                for (i in foodsViewModel.tempAmountFood.value!! until foodsViewModel.tempAmountFood.value!! + amount - 1) {
                    foodsViewModel.currentFoodsLiveData.value?.add(foodsViewModel.foodsLiveData.value!![i])

                }
                Log.d(TAG, "getTempFoods 4: ${foodsViewModel.currentFoodsLiveData.value?.size}")

            }
        }
        Log.d(TAG, "getTempFoods: ${foodsViewModel.currentFoodsLiveData.value?.size}")

    }

}