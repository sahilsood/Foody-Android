package com.example.foody.dto


import com.google.gson.annotations.SerializedName

data class FoodRecipe(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
) {
    data class Result(
        val id: Int,
        val image: String,
        val imageType: String,
        val nutrition: Nutrition,
        val title: String,
        val summary: String,
        val aggregateLikes: Int,
        val readyInMinutes: Int,
        val vegan: Boolean
    ) {
        data class Nutrition(
            val nutrients: List<Nutrient>
        ) {
            data class Nutrient(
                val amount: Double,
                val name: String,
                val unit: String
            )
        }
    }
}