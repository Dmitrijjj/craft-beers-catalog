package com.dimidroid.beerscatalog.ui.beers_favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dimidroid.beerscatalog.R
import com.dimidroid.beerscatalog.adapters.BeersCatalogAdapter
import com.dimidroid.beerscatalog.db.BeersDatabase
import com.dimidroid.beerscatalog.repository.BeersRepository
import com.dimidroid.beerscatalog.ui.beers_catalog.BeersCatalogFragmentDirections

class BeersFavouriteFragment : Fragment() {

    lateinit var viewModel: BeersFavouriteViewModel
    lateinit var favAdapter: BeersCatalogAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favourite_beers, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewFavourites)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = BeersRepository(BeersDatabase(requireContext()))
        val viewModelProviderFactory = FavouriteViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[BeersFavouriteViewModel::class.java]

        setupRecyclerView()

        favAdapter.setOnClickListener {
            val direction = BeersFavouriteFragmentDirections.actionNavigationFavouriteBeersToBeersDetailsFragment(it)
            findNavController().navigate(direction)
        }

        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val beerItem = favAdapter.differ.currentList[position]
                viewModel.deleteSavedBeer(beerItem)
                Toast.makeText(requireContext(), "Item deleted!", Toast.LENGTH_SHORT).show()
            }

        }

        ItemTouchHelper(itemTouchHelper).apply {
            attachToRecyclerView(recyclerView)
        }

        viewModel.getSavedBeers().observe(viewLifecycleOwner, Observer { beerItem ->
            favAdapter.differ.submitList(beerItem)
        })

    }

    private fun setupRecyclerView(){
        favAdapter = BeersCatalogAdapter()
        recyclerView.apply {
            adapter = favAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

}