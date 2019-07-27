package com.diegaspar.recipesbook.repo

import com.diegaspar.recipesbook.api.RecipesService
import com.diegaspar.recipesbook.persitence.RecipeDB
import com.diegaspar.recipesbook.persitence.RecipeDB.Companion.mapList
import com.diegaspar.recipesbook.persitence.RecipeDao

class RecipesRepo(private val recipesService: RecipesService, private val dao: RecipeDao) {

    private suspend fun searchRecipe(query: String, page: Int) =
        recipesService.search(query, page).await()

    suspend fun searchRecipesWithPagination(query: String, page: Int): List<RecipeDB> {
        if (query.isEmpty()) return listOf()

        val request = searchRecipe(query, page)
        return mapList(recipeList = request.items)
    }

    suspend fun saveRecipePersistence(recipe: RecipeDB) {
        dao.insert(recipe)
    }

    suspend fun getAllRecipesPersistence(): List<RecipeDB> {
        return dao.findAllRecipes()
    }

    suspend fun deleteRecipePersistence(recipe: RecipeDB) {
        dao.delete(recipe)
    }
}