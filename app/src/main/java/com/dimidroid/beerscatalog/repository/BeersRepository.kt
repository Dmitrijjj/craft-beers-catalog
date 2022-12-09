package com.dimidroid.beerscatalog.repository

import com.dimidroid.beerscatalog.api.RetrofitInstance
import com.dimidroid.beerscatalog.db.BeersDatabase

class BeersRepository(val db: BeersDatabase) {

    suspend fun getBeers(pageNum: Int) =
        RetrofitInstance.api.getBeers(pageNum)

}