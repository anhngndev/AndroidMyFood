package com.ftech.dev.android_my_food.ui.voucher

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ftech.dev.android_my_food.FoodDetailViewModel
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.data.model.Food
import com.ftech.dev.android_my_food.data.model.Voucher
import com.ftech.dev.android_my_food.databinding.ItemVoucherBinding
import com.ftech.dev.android_my_food.ui.home.FoodAdapter

class VoucherAdapter : RecyclerView.Adapter<VoucherAdapter.VoucherViewHolder>() {

    var list: MutableList<Voucher> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var callBack: VoucherListener? = null

    class VoucherViewHolder(val binding : ItemVoucherBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoucherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemVoucherBinding>(layoutInflater, R.layout.item_voucher, parent, false)
        return VoucherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VoucherViewHolder, position: Int) {
        val item = list[position]

        holder.binding.item = item
        holder.binding.listener = callBack
        holder.binding.position = position

    }

    override fun getItemCount()= list.size

    interface VoucherListener {
        fun onItemClick(index: Int, item: Voucher)

    }
}