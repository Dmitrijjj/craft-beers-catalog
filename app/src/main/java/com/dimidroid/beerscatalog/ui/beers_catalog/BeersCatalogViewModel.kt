package com.dimidroid.beerscatalog.ui.beers_catalog

import androidx.lifecycle.ViewModel
import com.dimidroid.beerscatalog.repository.BeersRepository

class BeersCatalogViewModel(
    val repository: BeersRepository
): ViewModel() {
}