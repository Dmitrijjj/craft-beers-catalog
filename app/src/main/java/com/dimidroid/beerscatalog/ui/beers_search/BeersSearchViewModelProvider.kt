package com.dimidroid.beerscatalog.ui.beers_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dimidroid.beerscatalog.repository.BeersRepository

class BeersSearchViewModelProvider(
    private val beersRepository: BeersRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BeersSearchViewModel(beersRepository) as T
    }
}
