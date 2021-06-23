package com.ftech.dev.android_my_food.ui.detailfood

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ftech.dev.android_my_food.FoodDetailViewModel
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.data.model.Food
import com.ftech.dev.android_my_food.databinding.BottomSheetDetailFoodBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FoodDetailBottomSheet : BottomSheetDialogFragment() {

    lateinit var binding: BottomSheetDetailFoodBinding
    private val detailViewModel: FoodDetailViewModel by activityViewModels()
    lateinit var food: Food

    companion object {
        const val TAG = "CustomBottomSheetDialogFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_detail_food, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imvCancel.setOnClickListener {
            dismiss()
        }

        binding.tvAddOrder.setOnClickListener {
            findNavController().navigate(R.id.action_detailFoodBottomSheet_to_oderDetailFragment)
        }

        detailViewModel.liveFood.observe(viewLifecycleOwner) { food ->
            binding.item = food
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}