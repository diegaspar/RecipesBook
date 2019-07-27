package com.diegaspar.recipesbook.di

import com.diegaspar.recipesbook.viewmodel.FavouritesRecipeViewModel
import com.diegaspar.recipesbook.viewmodel.SearchRecipeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SearchRecipeViewModel(get()) }
    viewModel { FavouritesRecipeViewModel(get()) }
}
