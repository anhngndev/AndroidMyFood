package com.ftech.dev.android_my_food.ui.detailfood

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ftech.dev.android_my_food.FoodDetailViewModel
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.data.model.Card
import com.ftech.dev.android_my_food.databinding.FragmentBigFoodDetailBinding
import com.ftech.dev.android_my_food.ui.home.CardAdapter
import com.ftech.dev.android_my_food.utils.DataFake

class BigFoodDetailFragment : BaseFragment<FragmentBigFoodDetailBinding>(),
    FoodImageAdapter.FoodImageListener {

    private val TAG = "BigFoodDetailFragment"
    private lateinit var foodImageAdapter: FoodImageAdapter
    private var foodImageList = mutableListOf<Int>()


    private val detailViewModel: FoodDetailViewModel by activityViewModels()
    override fun getLayoutId(): Int {
        return R.layout.fragment_big_food_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
        setStateBottomNavigation(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setAction()

    }

    override fun initView() {
        val imageFoodLayoutManager = StaggeredGridLayoutManager(1, RecyclerView.HORIZONTAL)
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvFood)

        detailViewModel.liveBigFood.observe(viewLifecycleOwner) { food ->
            binding.item = food
            foodImageList = food.image

            foodImageAdapter = FoodImageAdapter()
            foodImageAdapter.callBack = this
            foodImageAdapter.list = food.image

            Log.d(TAG, "initView: ${foodImageAdapter.list.size}")

            binding.rvFood.layoutManager = imageFoodLayoutManager
            binding.rvFood.adapter = foodImageAdapter
        }


    }

    override fun setAction() {

        binding.ivBack.setOnClickListener {

        }

        binding.tvSuperPartner.setOnClickListener {

        }
        binding.tvName.setOnClickListener {

        }
        binding.ivMake.setOnClickListener {

        }

    }

    override fun onItemClick(index: Int, item: Int) {
        findNavController().navigate(R.id.action_bigFoodDetailFragment_to_imageBigFoodDetailFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        setStateBottomNavigation(true)
    }


}