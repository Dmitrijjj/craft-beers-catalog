package com.dimidroid.beerscatalog.ui.beers_catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dimidroid.beerscatalog.repository.BeersRepository

class CatalogViewModelProviderFactory(
    private val beersRepository: BeersRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BeersCatalogViewModel(beersRepository) as T
    }

}
