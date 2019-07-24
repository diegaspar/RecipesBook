package com.diegaspar.recipesbook.repo

import com.diegaspar.recipesbook.api.RecipesService
import com.diegaspar.recipesbook.persitence.RecipeDB
import com.diegaspar.recipesbook.persitence.RecipeDB.Companion.mapList

class RecipesRepo(private val recipesService: RecipesService) {

    private suspend fun searchRecipe(query: String, page: Int) =
        recipesService.search(query, page).await()

    suspend fun searchRecipesWithPagination(query: String, page: Int): List<RecipeDB> {
        if (query.isEmpty()) return listOf()

        val request = searchRecipe(query, page)
        return mapList(recipeList = request.items)
    }
}