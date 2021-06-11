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
            "Indian Jumbo Sausage", R.drawable.food_image_1,
            "High vitamin", "35000", "95", resources
        )
        val food2 = Food(
            "Indian Jumbo Sausage", R.drawable.food_image_3,
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

        val food1 = FoodBig(
            "Indian Jumbo Sausage", R.drawable.food_image_1,
            5, "35000", "95", "45min"
        )
        val food2 = FoodBig(
            "Indian Jumbo Sausage", R.drawable.food_image_3,
            4, "40000", "91", "20min"
        )

        list.add(food1)
        list.add(food2)
        list.add(food1)
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
}