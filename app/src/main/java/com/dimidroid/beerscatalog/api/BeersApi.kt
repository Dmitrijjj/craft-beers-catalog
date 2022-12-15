package com.dimidroid.beerscatalog.api

import com.dimidroid.beerscatalog.models.BeerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BeersApi {

    @GET("v2/beers?")
    suspend fun getBeers(
        @Query("per_page")
        pageNumber: Int = 1
    ): Response<BeerResponse>

    @GET("v2/beers")
    suspend fun searchForBeers(
        @Query("beer_name")
        searchQuery: String,
    ): Response<BeerResponse>
}