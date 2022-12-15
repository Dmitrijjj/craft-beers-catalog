package com.dimidroid.beerscatalog.db

import androidx.room.TypeConverter
import com.dimidroid.beerscatalog.models.Amount
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
        val hops = toHop(lines[0])
        val malt = toMalt(lines[1])
        val yeast = lines[2]
        return Ingredients(listOf(hops), listOf(malt), yeast)
    }

    private fun toHop(string: String): Hop {
        return Hop(string,toAmount(string), string, string)
    }

    private fun toMalt(string: String): Malt{
        return Malt(toAmount(string), string)
    }

    private fun toAmount(string: String): Amount{
        return Amount(string, string)
    }

}