package com.dimidroid.beerscatalog.ui.beers_favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dimidroid.beerscatalog.models.BeerResponseItem
import com.dimidroid.beerscatalog.repository.BeersRepository
import kotlinx.coroutines.launch

class BeersFavouriteViewModel(
    val repository: BeersRepository
): ViewModel() {

    fun saveBeer(beerResponseItem: BeerResponseItem) = viewModelScope.launch {
        repository.upsertBeer(beerResponseItem)
    }

    fun getSavedBeers() = repository.getAllBeers()

    fun deleteSavedBeer(beerResponseItem: BeerResponseItem) = viewModelScope.launch{
        repository.deleteBeer(beerResponseItem)
    }
}