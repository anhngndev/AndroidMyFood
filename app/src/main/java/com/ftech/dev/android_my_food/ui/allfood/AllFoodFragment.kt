package com.ftech.dev.android_my_food.ui.allfood

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
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

    override fun isCanBackPress() = true

    override fun getLayoutId() = R.layout.fragment_all_food


    override fun initView() {
        getTempFoods(10)
        observer(foodsViewModel.tempFoodsLiveData) {
            Log.d(TAG, "initView: ${it?.size}")
            it?.let { list ->
                foodAdapter.list = list
            }
        }
//        Log.d(TAG, "initView: ${foodsViewModel.tempFoodsLiveData.value?.size}")
//        foodAdapter.list = foodsViewModel.tempFoodsLiveData.value!!
        foodAdapter.callBack = this
        binding.rcFood.adapter = foodAdapter

        binding.rcFood.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) { //1 for down
                    loadMore()
                    Log.d(TAG, "onScrolled: ")

                }
            }
        })


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

    fun loadMore(){
// goi khi keo den ptu cuoi cung cua current list
    }
    fun getTempFoods(amount: Int) {
        if (foodsViewModel.tempAmountFood.value == 0){
            if (foodsViewModel.foodsLiveData.value?.size!! > amount){
                foodsViewModel.tempAmountFood.value = amount
                for(i in 0 until amount - 1){
                    foodsViewModel.tempFoodsLiveData.value?.add(foodsViewModel.foodsLiveData.value!![i])
                    Log.d(TAG, "getTempFoods 0: ${foodsViewModel.tempFoodsLiveData.value?.size}")

                }
                Log.d(TAG, "getTempFoods 1: ${foodsViewModel.tempFoodsLiveData.value?.size}")

            } else{
                foodsViewModel.tempAmountFood.value = foodsViewModel.foodsLiveData.value?.size
                foodsViewModel.tempFoodsLiveData.value = foodsViewModel.foodsLiveData.value!!
                Log.d(TAG, "getTempFoods 2: ${foodsViewModel.tempFoodsLiveData.value?.size}")

            }
        }else{

            if ((foodsViewModel.tempAmountFood?.value!! + amount) > foodsViewModel.foodsLiveData.value?.size!! ){
                foodsViewModel.tempAmountFood.value = foodsViewModel.foodsLiveData.value?.size
                foodsViewModel.tempFoodsLiveData.value =foodsViewModel.foodsLiveData.value!!
                Log.d(TAG, "getTempFoods 3: ${foodsViewModel.tempFoodsLiveData.value?.size}")

            } else{
                for(i in foodsViewModel.tempAmountFood.value!! until foodsViewModel.tempAmountFood.value!! + amount - 1){
                    foodsViewModel.tempFoodsLiveData.value?.add(foodsViewModel.foodsLiveData.value!![i])

                }
                Log.d(TAG, "getTempFoods 4: ${foodsViewModel.tempFoodsLiveData.value?.size}")

            }
        }
        Log.d(TAG, "getTempFoods: ${foodsViewModel.tempFoodsLiveData.value?.size}")

    }

}