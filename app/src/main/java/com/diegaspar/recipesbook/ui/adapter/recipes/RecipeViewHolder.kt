package com.diegaspar.recipesbook.ui.adapter.recipes

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.diegaspar.recipesbook.persitence.RecipeDB
import com.diegaspar.recipesbook.ui.adapter.utils.setupViews
import kotlinx.android.synthetic.main.item_recipe.view.*

class RecipeViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {

    fun bind(
        recipe: RecipeDB?,
        listener: RecipeAdapter.OnClickListener
    ) {
        recipe?.let {
            setupViews(it, itemView)
            setListeners(listener, recipe)
        }
    }

    private fun setListeners(
        listener: RecipeAdapter.OnClickListener,
        recipe: RecipeDB
    ) {
        itemView.recipe_button.setOnClickListener {
            listener.onAddToFavouritesClicked(recipe)
        }
        itemView.recipe_parent.setOnClickListener {
            recipe.href?.let { it1 -> listener.onRecipeRowClicked(it1) }
        }
    }


}
