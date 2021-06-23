package com.ftech.dev.android_my_food.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.data.source.local.SearchEntity
import com.ftech.dev.android_my_food.databinding.ItemRecentSearchBinding

class RecentSearchAdapter : RecyclerView.Adapter<RecentSearchAdapter.RecentSearchViewHolder>() {

    var list: List<SearchEntity> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var callback: RecentSearchListener? = null

    class RecentSearchViewHolder(val binding: ItemRecentSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemRecentSearchBinding>(
            layoutInflater,
            R.layout.item_recent_search,
            parent,
            false
        )
        return RecentSearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {
        val item = list[position]
        holder.binding.item = item
    }

    override fun getItemCount() = list.size

    interface RecentSearchListener {
        fun onItemClick(index: Int, item: SearchEntity)

    }
}