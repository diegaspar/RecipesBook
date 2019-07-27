package com.diegaspar.recipesbook.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.diegaspar.recipesbook.api.NetworkState
import com.diegaspar.recipesbook.persitence.RecipeDB
import com.diegaspar.recipesbook.repo.RecipesRepo
import kotlinx.coroutines.*

class RecipeDataSource(
    private val repository: RecipesRepo,
    private val query: String,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, RecipeDB>() {

    private var supervisorJob = SupervisorJob()
    private val networkState = MutableLiveData<NetworkState>()
    private var retryQuery: (() -> Any)? = null //Keep the last query just in case it fails

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, RecipeDB>) {
        retryQuery = { loadInitial(params, callback) }
        executeQuery(1) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, RecipeDB>) {
        val page = params.key
        retryQuery = { loadAfter(params, callback) }
        executeQuery(page) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, RecipeDB>) {
        //Not implemented
    }

    private fun executeQuery(
        page: Int,
        callback: (List<RecipeDB>) -> Unit
    ) {
        networkState.postValue(NetworkState.RUNNING)
        scope.launch(getJobErrorHandler() + supervisorJob) {
            val recipes = repository.searchRecipesWithPagination(query, page)
            retryQuery = null
            networkState.postValue(NetworkState.SUCCESS)
            callback(recipes)
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, _ ->
        networkState.postValue(NetworkState.FAILED)
    }

    override fun invalidate() {
        super.invalidate()
        supervisorJob.cancelChildren()
    }

    fun getNetworkState(): LiveData<NetworkState> =
        networkState

    fun refresh() =
        this.invalidate()

    fun retryFailedQuery() {
        val prevQuery = retryQuery
        retryQuery = null
        prevQuery?.invoke()
    }

    fun saveRecipePersistence(recipe: RecipeDB?) {
        scope.launch(getJobErrorHandler() + supervisorJob) {
            recipe?.let { repository.saveRecipePersistence(it) }
        }
    }
}