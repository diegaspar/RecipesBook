package com.diegaspar.recipesbook.di

import com.diegaspar.recipesbook.persitence.AppDataBase
import org.koin.dsl.module

val roomModule = module {
    single { AppDataBase.getInstance(get()) }
    single { get<AppDataBase>().getRecipeDao() }
}