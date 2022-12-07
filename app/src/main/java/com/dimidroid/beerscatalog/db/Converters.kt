package com.dimidroid.beerscatalog.db

import androidx.room.TypeConverter
import com.dimidroid.beerscatalog.models.Hop
import com.dimidroid.beerscatalog.models.Ingredients
import com.dimidroid.beerscatalog.models.Malt

class Converters {

    @TypeConverter
    fun fromList(list: List<String>): String{
        return list.toString()
    }

    @TypeConverter
    fun toList(string: String): List<String>{
        return listOf(string)
    }

    @TypeConverter
    fun fromIngredients(ingredients: Ingredients): String {
        return ingredients.hops.toString() + "\n" +
                ingredients.malt.toString() + "\n" +
                ingredients.yeast
    }

    @TypeConverter
    fun toIngredients(string: String): Ingredients {
        val lines = string.lines()
        val hops = lines[0] as List<Hop>
        val malt = lines[1] as List<Malt>
        val yeast = lines[2]
        return Ingredients(hops, malt, yeast)
    }

}