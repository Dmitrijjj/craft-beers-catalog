package com.dimidroid.beerscatalog.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "beers_table")
data class BeerResponseItem(
    //for icon in recyclerView
    val isFavourite: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("abv")
    val abv: Double,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("food_pairing")
    val foodPairing: List<String>,
    @SerializedName("ingredients")
    val ingredients: Ingredients
)