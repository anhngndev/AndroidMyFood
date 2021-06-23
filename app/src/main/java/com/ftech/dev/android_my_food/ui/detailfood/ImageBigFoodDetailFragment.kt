package com.ftech.dev.android_my_food.ui.detailfood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ftech.dev.android_my_food.FoodDetailViewModel
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.databinding.FragmentImageBigFoodDetailBinding

class ImageBigFoodDetailFragment : BaseFragment<FragmentImageBigFoodDetailBinding>(),
    FoodImageAdapter.FoodImageListener {

    private val detailViewModel: FoodDetailViewModel by activityViewModels()
    private lateinit var foodImageAdapter: FoodImageAdapter
    private var foodImageList = mutableListOf<Int>()

    override fun isCanBackPress() = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun setAction() {
        binding.ivBack.setOnClickListener {
            onBackPress()
        }

    }

    override fun initView() {
        val imageFoodLayoutManager = StaggeredGridLayoutManager(1, RecyclerView.HORIZONTAL)
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.rcFoodImage)

        detailViewModel.liveBigFood.observe(viewLifecycleOwner) {
            foodImageAdapter = FoodImageAdapter()
            foodImageAdapter.callBack = this
            foodImageAdapter.list = it.image

            binding.rcFoodImage.layoutManager = imageFoodLayoutManager
            binding.rcFoodImage.adapter = foodImageAdapter
        }

    }

    override fun getLayoutId() = R.layout.fragment_image_big_food_detail

    override fun onItemClick(index: Int, item: Int) {

    }

}