package com.example.foody.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.foody.data.database.RecipesEntity
import com.example.foody.dto.FoodRecipe
import com.example.foody.util.NetworkResult

class RecipesBinding {

    companion object {

        @BindingAdapter("readApiResponse", "readDatabase", requireAll = true)
        @JvmStatic
        fun errorImageViewVisibility(
            imageView: ImageView,
            networkResult: NetworkResult<FoodRecipe>?,
            database: List<RecipesEntity>?
        ) {
            if (networkResult is NetworkResult.Error && database.isNullOrEmpty()) {
                imageView.visibility = View.VISIBLE
            } else if (networkResult is NetworkResult.Loading) {
                imageView.visibility = View.INVISIBLE
            } else if (networkResult is NetworkResult.Success) {
                imageView.visibility = View.INVISIBLE
            }
        }

        @BindingAdapter("readApiResponse2", "readDatabase2", requireAll = true)
        @JvmStatic
        fun errorTextViewVisibility(
            textView: TextView,
            networkResult: NetworkResult<FoodRecipe>?,
            database: List<RecipesEntity>?
        ) {
            if (networkResult is NetworkResult.Error && database.isNullOrEmpty()) {
                textView.visibility = View.VISIBLE
                textView.text = networkResult.message.toString()
            } else if (networkResult is NetworkResult.Loading) {
                textView.visibility = View.INVISIBLE
            } else if (networkResult is NetworkResult.Success) {
                textView.visibility = View.INVISIBLE
            }
        }

    }

}