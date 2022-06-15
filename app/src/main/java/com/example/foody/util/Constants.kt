package com.example.foody.util

class Constants {

    companion object {

        internal const val BASE_URL = "https://api.spoonacular.com/"

        // Api query keys
        internal const val QUERY_NUMBER = "number"
        internal const val QUERY_API_KEY = "apiKey"
        internal const val QUERY_TYPE = "type"
        internal const val QUERY_DIET = "diet"
        internal const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
        internal const val QUERY_FILL_INGREDIENTS = "fillIngredients"

        //Room database
        internal const val DATABASE_NAME = "recipes_database"
        internal const val TABLE_NAME = "recipes_table"

    }

}
