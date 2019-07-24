package com.diegaspar.recipesbook.di

import com.diegaspar.recipesbook.repo.RecipesRepo
import org.koin.dsl.module

val repositoryModule = module {
    factory { RecipesRepo(get()) }
}