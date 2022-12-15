package com.dimidroid.beerscatalog.ui.beers_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dimidroid.beerscatalog.models.BeerResponseItem
import com.dimidroid.beerscatalog.repository.BeersRepository
import kotlinx.coroutines.launch

class BeersDetailsViewModel(
    val repository: BeersRepository
): ViewModel() {

    fun saveBeer(beerItem: BeerResponseItem) = viewModelScope.launch {
        repository.upsertBeer(beerItem)
    }
}