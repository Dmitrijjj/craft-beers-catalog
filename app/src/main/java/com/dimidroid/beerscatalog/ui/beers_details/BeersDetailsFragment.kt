package com.dimidroid.beerscatalog.ui.beers_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dimidroid.beerscatalog.R
import com.dimidroid.beerscatalog.db.BeersDatabase
import com.dimidroid.beerscatalog.repository.BeersRepository

class BeersDetailsFragment : Fragment() {

    lateinit var viewModel: BeersDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_beers_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = BeersRepository(BeersDatabase(requireContext()))
        val viewModelProviderFactory = DetailsViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[BeersDetailsViewModel::class.java]

    }

}