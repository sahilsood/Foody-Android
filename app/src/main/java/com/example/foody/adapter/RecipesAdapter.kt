package com.example.foody.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foody.databinding.RecipesRowItemBinding
import com.example.foody.dto.FoodRecipe
import com.example.foody.util.RecipesDiffUtil

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.MyViewHolder>() {
    private var recipe = emptyList<FoodRecipe.Result>()

    class MyViewHolder(private val binding: RecipesRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(result: FoodRecipe.Result) {
                binding.result = result
                binding.executePendingBindings()
            }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecipesRowItemBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentResult = recipe[position]
        holder.bind(currentResult)
    }

    override fun getItemCount(): Int {
        return recipe.size
    }

    fun setData(foodRecipe: FoodRecipe) {
        val diffUtil = RecipesDiffUtil(oldList = recipe, newList = foodRecipe.results)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        recipe = foodRecipe.results
        diffUtilResult.dispatchUpdatesTo(this)
    }
}