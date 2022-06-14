package com.example.foody.viewmodel

import android.app.Application
import android.content.res.Resources
import androidx.lifecycle.AndroidViewModel
import com.example.foody.R

private const val QUERY_NUMBER = "number"
private const val QUERY_API_KEY = "apiKey"
private const val QUERY_TYPE = "type"
private const val QUERY_DIET = "diet"
private const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
private const val QUERY_FILL_INGREDIENTS = "fillIngredients"

class RecipesViewModel(application: Application) : AndroidViewModel(application) {

    fun applyQueries(resources: Resources): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries[QUERY_NUMBER] = "50"
        queries[QUERY_API_KEY] = resources.getString(R.string.api_key)
        queries[QUERY_TYPE] = "snack"
        queries[QUERY_DIET] = "vegan"
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        return queries
    }

}