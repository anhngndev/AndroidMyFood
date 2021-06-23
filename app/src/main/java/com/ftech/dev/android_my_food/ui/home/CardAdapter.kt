package com.ftech.dev.android_my_food.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.data.model.Card
import com.ftech.dev.android_my_food.databinding.ItemCardBinding

class CardAdapter(
    var list: MutableList<Card>,
    var callBack: CardListener
) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemCardBinding>(
            layoutInflater,
            R.layout.item_card,
            parent,
            false
        )
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holderCard: CardViewHolder, position: Int) {
        var item = list[position]
        holderCard.binding.item = item

        holderCard.binding.itemListener = callBack
        holderCard.binding.itemPosition = position
    }

    override fun getItemCount() = list.size

    class CardViewHolder(val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root)

    interface CardListener {
        fun onItemClick(index: Int, item: Card)


    }
}
