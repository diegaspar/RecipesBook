package com.diegaspar.recipesbook.ui.adapter.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegaspar.recipesbook.R
import com.diegaspar.recipesbook.persitence.RecipeDB
import com.diegaspar.recipesbook.ui.adapter.utils.setupViews
import kotlinx.android.synthetic.main.item_recipe.view.*

class RecipeFavouriteViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_recipe, parent, false)) {

    fun bind(
        recipe: RecipeDB?,
        listener: RecipeFavouritesAdapter.OnItemClickListener
    ) {
        recipe?.let {
            setupViews(it, itemView)
            itemView.recipe_button.text = itemView.context.getString(R.string.Delete)
            setListeners(listener, recipe)
        }
    }

    private fun setListeners(
        listener: RecipeFavouritesAdapter.OnItemClickListener,
        recipe: RecipeDB
    ) {
        itemView.recipe_button.setOnClickListener {
            listener.onDeleteClick(recipe)
        }
        itemView.recipe_parent.setOnClickListener {
            recipe.href?.let { it1 -> listener.onRecipeRowClicked(it1) }
        }
    }
}