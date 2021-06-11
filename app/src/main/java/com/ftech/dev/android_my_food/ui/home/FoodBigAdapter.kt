package com.ftech.dev.android_my_food.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.data.model.FoodBig
import com.ftech.dev.android_my_food.databinding.ItemBigFoodBinding

class FoodBigAdapter: RecyclerView.Adapter<FoodBigAdapter.FoodBigViewHolder>() {


    var list: MutableList<FoodBig> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var callBack: FoodBigListener? = null

    class FoodBigViewHolder(val binding: ItemBigFoodBinding) : RecyclerView.ViewHolder(binding.root) {

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodBigViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemBigFoodBinding>(
            layoutInflater,
            R.layout.item_big_food,
            parent,
            false
        )
        return FoodBigViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodBigViewHolder, position: Int) {
        val item = list[position]
        holder.binding.item = item
        holder.binding.itemPosition = position
        holder.binding.itemListener = callBack
    }

    override fun getItemCount() = list.size

    interface FoodBigListener {
        fun onItemClick(index: Int, item: FoodBig)

    }
}