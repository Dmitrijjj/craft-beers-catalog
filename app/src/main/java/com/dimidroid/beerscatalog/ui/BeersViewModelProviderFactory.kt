package com.dimidroid.beerscatalog.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dimidroid.beerscatalog.repository.BeersRepository

class BeersViewModelProviderFactory(
    private val beersRepository: BeersRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BeersViewModel(beersRepository) as T
    }

}