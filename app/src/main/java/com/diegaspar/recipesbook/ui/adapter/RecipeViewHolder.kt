package com.diegaspar.recipesbook.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.diegaspar.recipesbook.persitence.RecipeDB
import com.diegaspar.recipesbook.utils.ImageUtils.Companion.loadImage
import kotlinx.android.synthetic.main.item_recipe.view.*

class RecipeViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {

    fun bind(
        recipe: RecipeDB?,
        listener: RecipeAdapter.OnClickListener
    ) {
        recipe?.let {
            it.thumbnail?.let { it1 -> loadImage(it1, itemView.recipe_thumb, itemView.context) }
            itemView.recipe_title.text = it.title
            itemView.recipe_ingredients.text = it.ingredients
            itemView.setOnClickListener { listener.onAddToFavouritesClicked(recipe.title) }
        }
    }
}