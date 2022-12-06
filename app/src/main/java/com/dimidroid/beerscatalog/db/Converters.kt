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

    //function TypeConverter has never used error
    @TypeConverter
    fun fromIngredients(hops: List<Hop>, malt: List<Malt>, yeast: String): String {
        val hopsStr = hops.joinToString(",")
        val maltStr = malt.joinToString(",")
        return hopsStr + "" + maltStr + "" + yeast
    }
    //error here
    @TypeConverter
    fun toIngredients(string: String): Ingredients {
        val lines = string.lines()
        val hops = lines[0] as List<Hop>
        val malts = lines[1] as List<Malt>
        val yeast = lines[2]
        return Ingredients(hops, malts, yeast)
    }

}