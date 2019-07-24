package com.diegaspar.recipesbook.api

import com.diegaspar.recipesbook.model.NetworkResult
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesService {

    @GET("api/")
    fun search(
        @Query("i") query: String,
        @Query("p") page: Int
    ): Deferred<NetworkResult>
}