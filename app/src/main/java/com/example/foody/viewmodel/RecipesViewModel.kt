package com.example.foody.viewmodel

import android.app.Application
import android.content.res.Resources
import androidx.lifecycle.AndroidViewModel
import com.example.foody.R
import com.example.foody.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.example.foody.util.Constants.Companion.QUERY_API_KEY
import com.example.foody.util.Constants.Companion.QUERY_DIET
import com.example.foody.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.example.foody.util.Constants.Companion.QUERY_NUMBER
import com.example.foody.util.Constants.Companion.QUERY_TYPE

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