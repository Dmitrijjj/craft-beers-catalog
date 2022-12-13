package com.dimidroid.beerscatalog.repository

import com.dimidroid.beerscatalog.api.RetrofitInstance
import com.dimidroid.beerscatalog.db.BeersDatabase
import com.dimidroid.beerscatalog.models.BeerResponseItem

class BeersRepository(val db: BeersDatabase) {

    suspend fun getBeers(pageNum: Int) =
        RetrofitInstance.api.getBeers(pageNum)

    suspend fun upsertBeer(beerResponseItem: BeerResponseItem) =
        db.getBeerDao().upsertBeer(beerResponseItem)

    fun getAllBeers() = db.getBeerDao().getAllBeers()

    suspend fun deleteBeer(beerResponseItem: BeerResponseItem) =
        db.getBeerDao().deleteBeer(beerResponseItem)

    suspend fun searchForBeers(searchQuery: String, pageNum: Int) =
        RetrofitInstance.api.searchForBeers(searchQuery, pageNum)

}