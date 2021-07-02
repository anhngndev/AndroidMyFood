package com.ftech.dev.android_my_food.ui.orderhistory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.data.model.Order
import com.ftech.dev.android_my_food.data.source.local.OrderEntity
import com.ftech.dev.android_my_food.databinding.ItemOrderBinding
import java.util.*

class OrderAdapter : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    var list: MutableList<OrderEntity> = mutableListOf()
        set(value) {
            field = value
            val status = liveOrderStatus.value
            notifyDataSetChanged()
        }

    var callBack: OrderListener? = null

    var liveOrderStatus = MutableLiveData<Stack<OrderEntity>>()

    class OrderViewHolder(val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    interface OrderListener {
        fun onItemClick(index: Int, item: OrderEntity)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemOrderBinding>(
            layoutInflater,
            R.layout.item_order,
            parent,
            false
        )
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val item = list[position]
        holder.binding.item = item
        holder.binding.itemPosition = position
        holder.binding.itemListener = callBack
        holder.binding.liveOrderStatus = liveOrderStatus
    }

    override fun getItemCount() = list.size
}