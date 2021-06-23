package com.ftech.dev.android_my_food.data.model

import androidx.room.Entity


@Entity
class Food(
    var name: String,
    var image: Int,
    var vitamin: String,
    var price: Int,
    var ratio: String,
    var resources: MutableList<String>

) {

    fun formatResource(): String {
        var allResource = ""
        for (i in resources) {
            allResource += "$i+ "

        }
        val i = allResource.length
        allResource = allResource.substring(0, i - 2)
        return allResource
    }

    fun getFormatPrice(): String {
        return "IDR  $price (+ Tax)"
    }

    fun getFormatPriceTotal(): String {
        return "IDR " + (price.toInt() * 1.1).toInt()
    }
}