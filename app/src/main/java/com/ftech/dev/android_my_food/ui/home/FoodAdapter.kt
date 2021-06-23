package com.ftech.dev.android_my_food.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.data.model.Food
import com.ftech.dev.android_my_food.databinding.ItemFoodBinding

class FoodAdapter : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    var list: MutableList<Food> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var callBack: FoodListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodAdapter.FoodViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemFoodBinding>(
            layoutInflater,
            R.layout.item_food,
            parent,
            false
        )
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holderFood: FoodAdapter.FoodViewHolder, position: Int) {
        val item = list[position]
        holderFood.binding.item = item
        holderFood.binding.itemPosition = position
        holderFood.binding.itemListener = callBack

        holderFood.binding.tvName.setOnLongClickListener {

            return@setOnLongClickListener false
        }
    }

    override fun getItemCount() = list.size

    class FoodViewHolder(val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    interface FoodListener {
        fun onItemClick(index: Int, item: Food)

    }


}

