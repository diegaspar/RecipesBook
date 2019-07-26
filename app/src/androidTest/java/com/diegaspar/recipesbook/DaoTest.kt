package com.diegaspar.recipesbook

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.diegaspar.recipesbook.persitence.AppDataBase
import com.diegaspar.recipesbook.persitence.RecipeDB
import com.diegaspar.recipesbook.persitence.RecipeDao
import io.reactivex.internal.util.NotificationLite.getValue
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DaoTest {

    private lateinit var recipeDao: RecipeDao
    private lateinit var db: AppDataBase
    private lateinit var recipeDB: RecipeDB
    private lateinit var recipeDB2: RecipeDB

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDataBase::class.java
        ).build()
        recipeDao = db.getRecipeDao()
        recipeDB = RecipeDB("Recipe1", "href1", "TitleTitleTitle", "BodyBodyBody")
        recipeDB2 = RecipeDB("Recipe2", "href2", "TitleTitleTitle", "BodyBodyBody")
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun should_Insert_Recipe_Item() {
        recipeDao.insert(recipeDB)

        val recipeDBTest = getValue<RecipeDB>(recipeDao.findRecipeById(recipeDB.title))
        Assert.assertEquals(recipeDBTest.title, recipeDB.title)
    }

    @Test
    @Throws(Exception::class)
    fun should_Get_All_Posts() {
        recipeDao.insert(recipeDB)
        recipeDao.insert(recipeDB2)

        val recipeDBTest = getValue<List<RecipeDB>>(recipeDao.findAllRecipes())
        Assert.assertEquals(recipeDBTest.size, 2)
    }

    @Test
    @Throws(Exception::class)
    fun should_Replace_Post_Post() {
        recipeDao.insert(recipeDB)
        recipeDao.insert(recipeDB)

        val recipeDBTest = getValue<List<RecipeDB>>(recipeDao.findAllRecipes())
        Assert.assertEquals(recipeDBTest.size, 1)
    }

    @Test
    @Throws(Exception::class)
    fun should_Delete_Post() {
        recipeDao.insert(recipeDB)
        recipeDao.delete(recipeDB)
        val recipeDBTest = getValue<RecipeDB>(recipeDao.findRecipeById(recipeDB.title))
        Assert.assertNull(recipeDBTest)
    }

    @Test
    @Throws(Exception::class)
    fun should_Delete_All_Data() {
        recipeDao.insert(recipeDB)

        recipeDao.deleteAllRecipeData()
        Assert.assertEquals(recipeDao.getRecipesCount(), 0)
    }
}