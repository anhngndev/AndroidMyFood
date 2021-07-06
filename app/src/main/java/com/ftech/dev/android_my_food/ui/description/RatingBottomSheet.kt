package com.ftech.dev.android_my_food.ui.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.databinding.BottomSheetFoodDetailBinding
import com.ftech.dev.android_my_food.databinding.BottomSheetRatingBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RatingBottomSheet: BottomSheetDialogFragment() {

    lateinit var binding: BottomSheetRatingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_rating, container, false)
        return binding.root
    }


}