package com.diegaspar.recipesbook.persitence

import androidx.room.*
import com.diegaspar.recipesbook.utils.Constants

@Dao
interface RecipeDao {

    @Query("SELECT * FROM Recipe")
    fun findAllRecipes(): List<RecipeDB>

    @Query("SELECT count(*) FROM ${Constants.TABLE_RECIPES}")
    fun getRecipesCount(): Int

    @Query("SELECT * FROM Recipe WHERE title = :recipeId")
    fun findRecipeById(recipeId: Long): RecipeDB

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recipeDB: RecipeDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(recipes: ArrayList<RecipeDB>)

    @Delete
    fun delete(recipeDB: RecipeDB)

    @Query("DELETE FROM ${Constants.TABLE_RECIPES}")
    fun deleteAllRecipeData()

}