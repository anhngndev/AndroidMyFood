package com.ftech.dev.android_my_food.ui.detailfood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ftech.dev.android_my_food.DetailFoodViewModel
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.databinding.BottomSheetDetailFoodBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DetailFoodBottomSheet : BottomSheetDialogFragment() {

    lateinit var binding : BottomSheetDetailFoodBinding
    private val viewModel: DetailFoodViewModel by activityViewModels()

    companion object {
        const val TAG = "CustomBottomSheetDialogFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_detail_food, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imvCancel.setOnClickListener {
            dismiss()
        }

        viewModel.liveFood.observe(viewLifecycleOwner) {food->
            binding.apply {
                tvName.text = food.name
                imvFood.setImageResource(food.image)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}