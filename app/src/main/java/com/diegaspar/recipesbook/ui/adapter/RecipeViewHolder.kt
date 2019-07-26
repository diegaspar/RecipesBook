package com.diegaspar.recipesbook.ui.adapter

import android.animation.Animator
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.diegaspar.recipesbook.extension.gone
import com.diegaspar.recipesbook.extension.visible
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
            itemView.recipe_animation_heart.gone()
            itemView.recipe_title.text = it.title
            itemView.recipe_ingredients.text = it.ingredients
            itemView.recipe_button_add.setOnClickListener {
                setupHeartLottieAnimation()
                listener.onAddToFavouritesClicked(recipe)
            }
            itemView.setOnClickListener {
                recipe.href?.let { it1 -> listener.onRecipeRowClicked(it1) }
            }
        }
    }

    private fun setupHeartLottieAnimation() {
        itemView.recipe_animation_heart.visible()
        itemView.recipe_animation_heart.playAnimation()
        itemView.recipe_animation_heart.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                itemView.recipe_animation_heart.gone()
            }

            override fun onAnimationCancel(p0: Animator?) {}

            override fun onAnimationStart(p0: Animator?) {}

        })
    }
}