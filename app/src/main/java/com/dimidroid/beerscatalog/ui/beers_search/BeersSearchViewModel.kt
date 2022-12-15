package com.dimidroid.beerscatalog.ui.beers_search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dimidroid.beerscatalog.models.BeerResponse
import com.dimidroid.beerscatalog.repository.BeersRepository
import com.dimidroid.beerscatalog.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class BeersSearchViewModel(
    val repository: BeersRepository
): ViewModel() {

    val searchCraftBeer: MutableLiveData<Resource<BeerResponse>> = MutableLiveData()

    fun searchForBeers(searchQuery: String) = viewModelScope.launch {
        searchCraftBeer.postValue(Resource.Loading())
        val response = repository.searchForBeers(searchQuery)
        searchCraftBeer.postValue(handleBeersResponse(response))
    }

    private fun handleBeersResponse(response: Response<BeerResponse>): Resource<BeerResponse> {
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}