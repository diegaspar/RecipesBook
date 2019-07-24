package com.diegaspar.recipesbook.persitence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.diegaspar.recipesbook.model.Recipe
import com.diegaspar.recipesbook.persitence.AppDataBase.Companion.DB_VERSION

@Database(entities = [Recipe::class], version = DB_VERSION, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getRecipeDao(): RecipeDao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "customRoomDB.db"
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: build(context).also { INSTANCE = it }
            }

        private fun build(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, DB_NAME)
                .addMigrations(MIGRATION_1_TO_2)
                .build()

        //In case app has to migrate to a new DBDao
        private val MIGRATION_1_TO_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }
    }
}