package com.dimidroid.beerscatalog.ui.beers_catalog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dimidroid.beerscatalog.R
import com.dimidroid.beerscatalog.db.BeersDatabase
import com.dimidroid.beerscatalog.repository.BeersRepository
import com.dimidroid.beerscatalog.ui.BeersViewModelProviderFactory

class BeersCatalogFragment : Fragment(R.layout.fragment_beers_catalog) {

    lateinit var viewModel: BeersCatalogViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = BeersRepository(BeersDatabase(requireContext()))
        val viewModelProviderFactory = CatalogViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[BeersCatalogViewModel::class.java]

    }

}