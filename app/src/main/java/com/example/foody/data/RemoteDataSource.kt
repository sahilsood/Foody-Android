package com.example.foody.data

import android.util.Log
import com.example.foody.data.network.FoodRecipeApi
import com.example.foody.dto.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipeApi
) {

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        val result = foodRecipesApi.getRecipes(queries)
        Log.i("okhttp", result.toString())
        return result
    }

}