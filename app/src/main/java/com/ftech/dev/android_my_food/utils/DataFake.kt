package com.ftech.dev.android_my_food.utils

import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.data.model.Card
import com.ftech.dev.android_my_food.data.model.Food
import com.ftech.dev.android_my_food.data.model.FoodBig

object DataFake {

    fun getFoodData(): MutableList<Food> {

        val list = mutableListOf<Food>()
        val resources = mutableListOf<String>("Sausage", "Pepper", "Oregano", "Marinade", "Cabbage")

        val food1 = Food(
            "Omelet", R.drawable.food_image_1,
            "High vitamin", "35000", "95", resources
        )
        val food2 = Food(
            "Stir-fried vegetable", R.drawable.food_image_3,
            "High vitamin", "40000", "91", resources
        )

        list.add(food1)
        list.add(food2)
        list.add(food1)
        list.add(food2)

        return list
    }

    fun getBigFoodData(): MutableList<FoodBig> {

        val list = mutableListOf<FoodBig>()

        val list1 = mutableListOf<Int>(
            R.drawable.omelet_1,
            R.drawable.omelet_2,
            R.drawable.omelet_3,
            R.drawable.omelet_3,
            R.drawable.omelet_3
        )

        val list2 = mutableListOf<Int>(
            R.drawable.vegetable_1,
            R.drawable.vegetable_2,
            R.drawable.vegetable_3,
            R.drawable.vegetable_3
        )

        val list3 = mutableListOf<Int>(
            R.drawable.cake_1,
            R.drawable.cake_2,
            R.drawable.cake_1,
            R.drawable.cake_1
        )

        val food1 = FoodBig(
            "Omelet", list1,
            5, "5000", "95", "45min"
        )
        val food2 = FoodBig(
            "Stir-fried vegetable", list2,
            4, "4000", "91", "20min"
        )

        val food3 = FoodBig(
            "Moon Cake", list3,
            4, "15000", "98", "10min"
        )

        list.add(food1)
        list.add(food2)
        list.add(food3)
        list.add(food2)

        return list
    }

    fun getCardData(): MutableList<Card> {
        val list = mutableListOf<Card>()
        val card = Card(R.drawable.card_image_1)
        val card2 = Card(R.drawable.card_image_2)
        val card3 = Card(R.drawable.card_image_3)
        list.add(card)
        list.add(card2)
        list.add(card3)
        list.add(card3)
        list.add(card2)

        return list
    }

    fun getImageFoodData(): MutableList<Int> {
        val list = mutableListOf<Int>()

        list.add(R.drawable.card_image_1)
        list.add(R.drawable.card_image_2)
        list.add(R.drawable.card_image_3)
        return list
    }
}