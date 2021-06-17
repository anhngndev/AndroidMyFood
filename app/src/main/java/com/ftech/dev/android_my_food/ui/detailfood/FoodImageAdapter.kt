package com.ftech.dev.android_my_food.ui.detailfood

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.databinding.FoodImageItemBinding
import com.ftech.dev.android_my_food.databinding.ItemFoodBinding
import com.ftech.dev.android_my_food.ui.home.FoodAdapter

class FoodImageAdapter : RecyclerView.Adapter<FoodImageAdapter.FoodImageViewHolder>() {
    var list: MutableList<Int> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var callBack: FoodImageListener? = null

    override fun getItemCount() = list.size



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodImageViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<FoodImageItemBinding>(
            layoutInflater,
            R.layout.food_image_item,
            parent,
            false
        )
        return FoodImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodImageViewHolder, position: Int) {
        val item = list[position]
        holder.binding.item = item
        holder.binding.itemPosition = position
        holder.binding.itemListener = callBack
    }


    class FoodImageViewHolder(val binding: FoodImageItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    interface FoodImageListener {
        fun onItemClick(index: Int, item: Int)

    }

}