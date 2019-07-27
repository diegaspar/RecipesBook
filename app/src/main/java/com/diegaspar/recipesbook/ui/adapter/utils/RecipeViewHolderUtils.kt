package com.diegaspar.recipesbook.ui.adapter.utils

import android.view.View
import com.diegaspar.recipesbook.extension.contains
import com.diegaspar.recipesbook.extension.gone
import com.diegaspar.recipesbook.extension.visible
import com.diegaspar.recipesbook.persitence.RecipeDB
import com.diegaspar.recipesbook.utils.Constants
import com.diegaspar.recipesbook.utils.ImageUtils
import kotlinx.android.synthetic.main.item_recipe.view.*

fun containsLactoseIngredients(ingredients: String?): Boolean {
    return ingredients?.contains(listOf(Constants.CHEESE, Constants.MILK)) ?: false
}

fun setupViews(it: RecipeDB, itemView: View) {
    it.thumbnail?.let { it1 -> ImageUtils.loadImage(it1, itemView.recipe_thumb, itemView.context) }
    itemView.recipe_title.text = it.title
    itemView.recipe_ingredients.text = it.ingredients
    checkLactose(it, itemView)
}

private fun checkLactose(it: RecipeDB, itemView: View) {
    if (containsLactoseIngredients(it.ingredients)) {
        itemView.recipe_label.visible()
    } else {
        itemView.recipe_label.gone()
    }
}