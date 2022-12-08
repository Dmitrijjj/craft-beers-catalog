package com.dimidroid.beerscatalog.ui.beers_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dimidroid.beerscatalog.R
import com.dimidroid.beerscatalog.db.BeersDatabase
import com.dimidroid.beerscatalog.repository.BeersRepository
import com.dimidroid.beerscatalog.ui.BeersViewModelProviderFactory

class BeersDetailsFragment : Fragment(R.layout.fragment_beers_details) {

    lateinit var viewModel: BeersDetailsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = BeersRepository(BeersDatabase(requireContext()))
        val viewModelProviderFactory = DetailsViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[BeersDetailsViewModel::class.java]

    }

}