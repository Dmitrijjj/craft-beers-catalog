package com.dimidroid.beerscatalog.ui.beers_favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dimidroid.beerscatalog.R
import com.dimidroid.beerscatalog.db.BeersDatabase
import com.dimidroid.beerscatalog.repository.BeersRepository
import com.dimidroid.beerscatalog.ui.BeersViewModelProviderFactory

class BeersFavouriteFragment : Fragment() {

    lateinit var viewModel: BeersFavouriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourite_beers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = BeersRepository(BeersDatabase(requireContext()))
        val viewModelProviderFactory = FavouriteViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[BeersFavouriteViewModel::class.java]

    }

}