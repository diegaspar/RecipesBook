package com.diegaspar.recipesbook.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.paging.LivePagedListBuilder
import com.diegaspar.recipesbook.api.NetworkState
import com.diegaspar.recipesbook.base.BaseViewModel
import com.diegaspar.recipesbook.datasource.RecipeDataSourceFactory
import com.diegaspar.recipesbook.repo.RecipesRepo
import com.diegaspar.recipesbook.utils.pagedListConfig

class SearchRecipeViewModel(repo: RecipesRepo) : BaseViewModel() {

    private val recipeDataSource = RecipeDataSourceFactory(repository = repo, scope = ioScope)

    val recipes = LivePagedListBuilder(recipeDataSource, pagedListConfig()).build()
    val networkState: LiveData<NetworkState>? = switchMap(recipeDataSource.source) { it.getNetworkState() }

    fun fetchRecipesByIngredients(query: String) {
        val search = query.trim()
        recipeDataSource.updateQuery(search)
    }

    fun refreshFailedRequest() =
        recipeDataSource.getSource()?.retryFailedQuery()

    fun refreshAllList() =
        recipeDataSource.getSource()?.refresh()

    fun getCurrentQuery() =
        recipeDataSource.getQuery()
}