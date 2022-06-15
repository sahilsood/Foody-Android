package com.example.foody.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foody.dto.FoodRecipe
import com.example.foody.util.Constants.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
class RecipesEntity(
    var foodRecipe: FoodRecipe
) {

    @PrimaryKey(autoGenerate = false)
    var id: Int = 0

}