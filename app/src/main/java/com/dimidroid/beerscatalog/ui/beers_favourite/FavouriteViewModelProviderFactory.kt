package com.dimidroid.beerscatalog.ui.beers_favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dimidroid.beerscatalog.repository.BeersRepository

class FavouriteViewModelProviderFactory(
    private val beersRepository: BeersRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BeersFavouriteViewModel(beersRepository) as T
    }

}
