package com.diegaspar.recipesbook.diTest

import com.diegaspar.recipesbook.persitence.RecipeDao
import com.diegaspar.recipesbook.repo.RecipesRepo
import org.koin.dsl.module

fun repoMockedDBModule(dao: RecipeDao) = module {
    factory { RecipesRepo(get(), dao) }
}