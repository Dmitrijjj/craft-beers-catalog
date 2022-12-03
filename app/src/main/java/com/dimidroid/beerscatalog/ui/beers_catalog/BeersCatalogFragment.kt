package com.dimidroid.beerscatalog.ui.beers_catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dimidroid.beerscatalog.R
import com.dimidroid.beerscatalog.databinding.FragmentBeersCatalogBinding

class BeersCatalogFragment : Fragment(R.layout.fragment_beers_catalog) {

    lateinit var beersCatalogViewModel: BeersCatalogViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //beersCatalogViewModel =
    }


}