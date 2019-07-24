package com.diegaspar.recipesbook

import android.app.Application
import com.diegaspar.recipesbook.di.networkModule
import com.diegaspar.recipesbook.di.repositoryModule
import com.diegaspar.recipesbook.di.roomModule
import com.diegaspar.recipesbook.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RecipesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            printLogger() // Koin Logger
            androidContext(this@RecipesApplication)
            modules(listOf(roomModule, viewModelModule, networkModule, repositoryModule))
        }
    }
}