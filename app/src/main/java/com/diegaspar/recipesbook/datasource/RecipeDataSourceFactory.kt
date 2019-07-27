package com.diegaspar.recipesbook.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.diegaspar.recipesbook.persitence.RecipeDB
import com.diegaspar.recipesbook.repo.RecipesRepo
import kotlinx.coroutines.CoroutineScope

class RecipeDataSourceFactory(
    private val repository: RecipesRepo,
    private var query: String = "",
    private val scope: CoroutineScope
) : DataSource.Factory<Int, RecipeDB>() {

    val source = MutableLiveData<RecipeDataSource>()

    override fun create(): DataSource<Int, RecipeDB> {
        val source = RecipeDataSource(repository, query, scope)
        this.source.postValue(source)
        return source
    }

    fun getQuery() = query

    fun getSource() = source.value

    fun updateQuery(query: String) {
        this.query = query
        getSource()?.refresh()
    }

    fun saveRecipePersistence(recipe: RecipeDB?) {
        getSource()?.saveRecipePersistence(recipe)
    }
}