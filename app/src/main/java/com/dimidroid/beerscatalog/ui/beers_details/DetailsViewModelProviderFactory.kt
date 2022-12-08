package com.dimidroid.beerscatalog.ui.beers_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dimidroid.beerscatalog.repository.BeersRepository
import com.dimidroid.beerscatalog.ui.BeersViewModel

class DetailsViewModelProviderFactory(
    private val beersRepository: BeersRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BeersDetailsViewModel(beersRepository) as T
    }

}
