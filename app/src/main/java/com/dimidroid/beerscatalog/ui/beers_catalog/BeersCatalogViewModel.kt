package com.dimidroid.beerscatalog.ui.beers_catalog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dimidroid.beerscatalog.models.BeerResponse
import com.dimidroid.beerscatalog.repository.BeersRepository
import com.dimidroid.beerscatalog.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class BeersCatalogViewModel(
    val repository: BeersRepository
): ViewModel() {

    val craftBeer: MutableLiveData<Resource<BeerResponse>> = MutableLiveData()
    private val craftBeerPage = 1

    init {
        getBeers()
    }

    private fun getBeers() = viewModelScope.launch {
        craftBeer.postValue(Resource.Loading())
        val response = repository.getBeers(craftBeerPage)
        craftBeer.postValue(handleBeersResponse(response))
    }

    private fun handleBeersResponse(response: Response<BeerResponse>): Resource<BeerResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}