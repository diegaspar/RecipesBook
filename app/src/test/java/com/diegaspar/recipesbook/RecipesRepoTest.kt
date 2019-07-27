package com.diegaspar.recipesbook

import com.diegaspar.recipesbook.base.BaseMockServerTest
import com.diegaspar.recipesbook.di.viewModelModule
import com.diegaspar.recipesbook.diTest.mockedAndroidContext
import com.diegaspar.recipesbook.diTest.networkMockedComponent
import com.diegaspar.recipesbook.diTest.repoMockedDBModule
import com.diegaspar.recipesbook.persitence.RecipeDao
import com.diegaspar.recipesbook.repo.RecipesRepo
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.test.inject
import org.mockito.Mockito.mock
import retrofit2.HttpException
import java.net.HttpURLConnection


@RunWith(JUnit4::class)
class RecipesRepoTest : BaseMockServerTest() {

    private val recipesRepo by inject<RecipesRepo>()
    var daoMocked = mock(RecipeDao::class.java)

    override fun setUp() {
        super.setUp()
        startKoin {
            modules(
                listOf(
                    viewModelModule,
                    networkMockedComponent(mockServer.url("/").toString()),
                    repoMockedDBModule(daoMocked)
                )
            )
        }
    }

    @Test
    fun search_recipes_result_ok() {
        mockHttpResponse("result_recipes_mocked.json", HttpURLConnection.HTTP_OK)
        runBlocking {
            val recipesListMocked = recipesRepo.searchRecipesWithPagination("query", 1)
            assertNotNull(recipesListMocked)
            assertEquals(recipesListMocked.isNullOrEmpty(), false)
        }
    }

    @Test
    fun search_recipe_result_ok_single_recipe() {
        mockHttpResponse("result_single_recipe_mocked.json", HttpURLConnection.HTTP_OK)
        runBlocking {
            val recipesListMocked = recipesRepo.searchRecipesWithPagination("query", 1)
            assertNotNull(recipesListMocked)
            assertEquals(recipesListMocked.isNullOrEmpty(), false)
            val recipe = recipesListMocked[0]
            assertEquals(recipe.title, "Roasted Garlic Grilling Sauce")
            assertEquals(recipe.href, "http://www.kraftfoods.com/kf/recipes/roasted-garlic-grilling-sauce-56344.aspx")
            assertEquals(recipe.ingredients, "Garlic, onions, hot sauce")
            assertEquals(recipe.thumbnail, "http://img.recipepuppy.com/634118.jpg")
        }
    }

    @Test(expected = HttpException::class)
    fun search_recipes_result_error() {
        mockHttpResponse("result_recipes_mocked.json", HttpURLConnection.HTTP_BAD_REQUEST)
        runBlocking {
            val recipesListMocked = recipesRepo.searchRecipesWithPagination("query", 1)
            assertEquals(recipesListMocked.isNullOrEmpty(), true)
        }
    }


}