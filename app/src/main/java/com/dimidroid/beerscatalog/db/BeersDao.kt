package com.dimidroid.beerscatalog.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dimidroid.beerscatalog.models.BeerResponseItem

@Dao
interface BeersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertBeer(beerResponseItem: BeerResponseItem): Long

    @Query("SELECT * FROM beers_table")
    fun getAllBeers(): LiveData<List<BeerResponseItem>>

    @Delete
    suspend fun deleteBeer(beerResponseItem: BeerResponseItem)

}