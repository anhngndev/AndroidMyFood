package com.ftech.dev.android_my_food.ui.orderdetail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.ftech.dev.android_my_food.FoodDetailViewModel
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.databinding.FragmentOrderDetailBinding
import com.ftech.dev.android_my_food.utils.observer

class OderDetailFragment :BaseFragment<FragmentOrderDetailBinding>() {

    private  val TAG = "OderDetailFragment"
    private val viewModelFoodDetail: FoodDetailViewModel by activityViewModels()
//    var total  = 0

//    val callback = requireActivity().onBackPressedDispatcher.addCallback(this){
//        handleOnBackPressed()
//    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_order_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
        setStateBottomNavigation(false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModelFoodDetail
        initView()

        setAction()

    }

    private fun setAction() {
        binding.imvZoomIn.setOnClickListener {
            Log.d(TAG, "setAction: ${viewModelFoodDetail.amount.value}")
            viewModelFoodDetail.upAmount()
        }
        binding.imvZoomOut.setOnClickListener {
            Log.d(TAG, "setAction: ${viewModelFoodDetail.amount.value}")
            viewModelFoodDetail.downAmount()
        }
        binding.tvTitle.setOnClickListener {
            Log.d(TAG, "setAction: ")

        }

        binding.imvBack.setOnClickListener {
//            callback.handleOnBackPressed()

        }
    }

    private fun initView() {
        observer(viewModelFoodDetail.liveFood){
            it?.let {food->
                binding.food = food
                binding.imvFood.setImageResource(food.image)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModelFoodDetail.resetAmount()
        setStateBottomNavigation(true)
    }

}

