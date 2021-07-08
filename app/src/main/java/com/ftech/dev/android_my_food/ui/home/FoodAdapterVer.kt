package com.ftech.dev.android_my_food.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.data.model.Food
import com.ftech.dev.android_my_food.databinding.ItemFoodHorBinding
import com.ftech.dev.android_my_food.databinding.ItemFoodVerBinding

class FoodAdapterVer : RecyclerView.Adapter<FoodAdapterVer.FoodViewHolder>() {

    private val TAG = "FoodAdapterVer"
    var list: MutableList<Food> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var callBack: FoodListener? = null
    var loadMore : ()->Unit={}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodAdapterVer.FoodViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemFoodVerBinding>(
            layoutInflater,
            R.layout.item_food_ver,
            parent,
            false
        )
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holderFood: FoodAdapterVer.FoodViewHolder, position: Int) {
        val item = list[position]
        holderFood.binding.item = item
        holderFood.binding.itemPosition = position
        holderFood.binding.itemListener = callBack

        holderFood.binding.tvName.setOnLongClickListener {

            return@setOnLongClickListener false
        }
    }

    override fun getItemCount() = list.size

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                layoutManager.let {
                    val lastShowed = layoutManager.findLastCompletelyVisibleItemPosition()
                    if (lastShowed == itemCount - 1 && dy > 0) {
                        Log.d(TAG, "onScrolled: ")
                        loadMore.invoke()
                    }
                }
            }
        })
    }


    class FoodViewHolder(val binding: ItemFoodVerBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    interface FoodListener {
        fun onItemClick(index: Int, item: Food)

    }


}

