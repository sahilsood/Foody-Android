package com.example.foody.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foody.databinding.RecipesRowItemBinding
import com.example.foody.dto.FoodRecipe
import com.example.foody.util.RecipesDiffUtil

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.MyViewHolder>() {
    private var recipes = emptyList<FoodRecipe.Result>()

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
        val currentRecipe = recipes[position]
        holder.bind(currentRecipe)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun setData(foodRecipe: FoodRecipe) {
        val diffUtil = RecipesDiffUtil(oldList = recipes, newList = foodRecipe.results)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        recipes = foodRecipe.results
        diffUtilResult.dispatchUpdatesTo(this)
    }
}