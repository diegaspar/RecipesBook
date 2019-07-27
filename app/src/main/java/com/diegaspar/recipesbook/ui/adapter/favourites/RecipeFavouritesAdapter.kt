package com.diegaspar.recipesbook.ui.adapter.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegaspar.recipesbook.persitence.RecipeDB

class RecipeFavouritesAdapter(
    private var items: List<RecipeDB>?,
    private var listener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RecipeFavouriteViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as RecipeFavouriteViewHolder).bind(items?.get(position), listener)

    override fun getItemCount(): Int {
        return if (items != null) {
            items!!.size
        } else 0
    }

    fun replaceData(items: List<RecipeDB>?) {
        this.items = items
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onDeleteClick(recipeDB: RecipeDB)
        fun onRecipeRowClicked(href: String)
    }

}