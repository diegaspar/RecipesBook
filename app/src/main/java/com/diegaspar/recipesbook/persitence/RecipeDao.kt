package com.diegaspar.recipesbook.persitence

import androidx.room.*
import com.diegaspar.recipesbook.utils.Constants

@Dao
interface RecipeDao {

    @Query("SELECT * FROM Recipe")
    suspend fun findAllRecipes(): List<RecipeDB>

    @Query("SELECT count(*) FROM ${Constants.TABLE_RECIPES}")
    suspend fun getRecipesCount(): Int

    @Query("SELECT * FROM Recipe WHERE title = :recipeId")
    suspend fun findRecipeById(recipeId: String): RecipeDB

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipeDB: RecipeDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(recipes: ArrayList<RecipeDB>)

    @Delete
    suspend fun delete(recipeDB: RecipeDB)

    @Query("DELETE FROM ${Constants.TABLE_RECIPES}")
    suspend fun deleteAllRecipeData()

}