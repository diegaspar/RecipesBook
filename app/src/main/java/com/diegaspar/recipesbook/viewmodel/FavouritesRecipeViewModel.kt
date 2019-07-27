package com.diegaspar.recipesbook.viewmodel

import androidx.lifecycle.MutableLiveData
import com.diegaspar.recipesbook.base.BaseViewModel
import com.diegaspar.recipesbook.persitence.RecipeDB
import com.diegaspar.recipesbook.repo.RecipesRepo
import kotlinx.coroutines.launch

class FavouritesRecipeViewModel(private val repo: RecipesRepo) : BaseViewModel() {

    var recipes = MutableLiveData<List<RecipeDB>>()

    fun loadRecipesPersistence() {
        ioScope.launch {
            val listRetrieved = repo.getAllRecipesPersistence()
            mainScope.launch {
                recipes.value = listRetrieved
            }
        }
    }

    fun deleteRecipePersistence(recipeDB: RecipeDB) {
        ioScope.launch {
            repo.deleteRecipePersistence(recipeDB)
            loadRecipesPersistence() //Reload data
        }
    }
}