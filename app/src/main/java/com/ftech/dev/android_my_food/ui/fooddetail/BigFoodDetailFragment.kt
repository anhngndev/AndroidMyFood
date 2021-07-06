package com.ftech.dev.android_my_food.ui.fooddetail

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ftech.dev.android_my_food.FoodDetailViewModel
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.data.model.Food
import com.ftech.dev.android_my_food.databinding.FragmentBigFoodDetailBinding
import com.ftech.dev.android_my_food.ui.home.FoodAdapterVer
import com.ftech.dev.android_my_food.utils.observer
import java.util.*

class BigFoodDetailFragment : BaseFragment<FragmentBigFoodDetailBinding>(),
    FoodImageAdapter.FoodImageListener,
    FoodAdapterVer.FoodListener{

    private val TAG = "BigFoodDetailFragment"
    private var foodImageAdapter = FoodImageAdapter()
    private var foodAdapter = FoodAdapterVer()
    private var foodImageList = mutableListOf<Int>()

    private val foodsViewModel: FoodDetailViewModel by activityViewModels()
    override fun getLayoutId(): Int {
        return R.layout.fragment_big_food_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun isCanBackPress() = true

    override fun initView() {
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvFood)

        foodsViewModel.liveBigFood.observe(viewLifecycleOwner) { food ->
            binding.item = food
            foodImageList = food.image
            foodImageAdapter.list = food.image

        }

        foodImageAdapter.callBack = this
        binding.rvFood.adapter = foodImageAdapter

        foodAdapter.list = performFilter(foodsViewModel.liveBigFood.value?.category!!)
        foodAdapter.callBack = this
        binding.rcFood.adapter = foodAdapter

//        observer(foodsViewModel.foodsLiveData) {
//            it?.let { list ->
//                foodAdapter.list = list
//            }
//        }
    }

    private fun performFilter(keySearch: String) : MutableList<Food>{
        var foodsTemp = mutableListOf<Food>()
        if (keySearch != "") {
            for (d in foodsViewModel.foodsLiveData.value!!) {
                if (d.id.lowercase(Locale.getDefault())
                        .contains(keySearch.lowercase(Locale.getDefault()))
                ) {
                    foodsTemp.add(d)
                }
            }
        } else {
            foodAdapter.list = foodsTemp
        }
        return foodsTemp
    }

    override fun setAction() {
        binding.ivBack.setOnClickListener {
            onBackPress()
        }
        binding.tvName.setOnClickListener {

        }
        binding.llRate.setOnClickListener {
            findNavController().navigate(R.id.action_bigFoodDetailFragment_to_ratingBottomSheet)
        }

    }

    override fun onItemClick(index: Int, item: Int) {
        findNavController().navigate(R.id.action_bigFoodDetailFragment_to_imageBigFoodDetailFragment)
    }

    override fun onItemClick(index: Int, item: Food) {
        foodsViewModel.liveFood.value = (item)
        foodsViewModel.amount.value = 1
        foodsViewModel.total.value = item.getPriceToInt()
        findNavController().navigate(R.id.action_bigFoodDetailFragment_to_oderDetailFragment)
    }

}