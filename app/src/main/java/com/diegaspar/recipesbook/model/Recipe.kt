package com.diegaspar.recipesbook.model

import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("title") val title: String,
    @SerializedName("href") val href: String,
    @SerializedName("ingredients") val ingredients: String,
    @SerializedName("thumbnail") val thumbnail: String
)