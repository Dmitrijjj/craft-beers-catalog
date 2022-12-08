package com.dimidroid.beerscatalog.ui

import androidx.lifecycle.ViewModel
import com.dimidroid.beerscatalog.repository.BeersRepository

class BeersViewModel(
    val repository: BeersRepository
): ViewModel() {
}