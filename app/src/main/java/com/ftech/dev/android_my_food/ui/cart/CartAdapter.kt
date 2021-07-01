package com.ftech.dev.android_my_food.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.data.source.local.ItemInCartEntity
import com.ftech.dev.android_my_food.data.source.local.SearchEntity
import com.ftech.dev.android_my_food.databinding.ItemCardBinding
import com.ftech.dev.android_my_food.databinding.ItemCartBinding
import com.ftech.dev.android_my_food.databinding.ItemRecentSearchBinding
import com.ftech.dev.android_my_food.ui.fooddetail.FoodImageAdapter
import com.ftech.dev.android_my_food.ui.search.RecentSearchAdapter

class CartAdapter : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    var list: MutableList<ItemInCartEntity> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var callBack: CartListener? = null

    class CartViewHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemCartBinding>(
            layoutInflater,
            R.layout.item_cart,
            parent,
            false
        )
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = list[position]
        holder.binding.item = item
        holder.binding.listener = callBack
    }

    override fun getItemCount() = list.size

    interface CartListener {
        fun onItemClick(index: Int, item: ItemInCartEntity)
        fun onDeleteItem(item: ItemInCartEntity)
    }

}