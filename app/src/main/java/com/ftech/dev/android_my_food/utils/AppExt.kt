package com.ftech.dev.android_my_food.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ftech.dev.android_my_food.data.model.Card
import com.ftech.dev.android_my_food.data.model.Food
import com.ftech.dev.android_my_food.data.model.FoodBig
import com.makeramen.roundedimageview.RoundedImageView


@BindingAdapter("image")
fun RoundedImageView.abc(item: Food) {
    setImageResource(item.image)
}

@BindingAdapter("image")
fun RoundedImageView.setImageBigFood(item: FoodBig) {
    setImageResource(item.image)
}

@BindingAdapter("image")
fun ImageView.abc(item: Card) {
    setImageResource(item.image)
}