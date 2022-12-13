package com.dimidroid.beerscatalog.ui.beers_search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dimidroid.beerscatalog.R
import com.dimidroid.beerscatalog.adapters.BeersSearchAdapter
import com.dimidroid.beerscatalog.db.BeersDatabase
import com.dimidroid.beerscatalog.models.BeerResponseItem
import com.dimidroid.beerscatalog.repository.BeersRepository
import com.dimidroid.beerscatalog.ui.beers_catalog.BeersCatalogViewModel
import com.dimidroid.beerscatalog.ui.beers_catalog.CatalogViewModelProviderFactory
import com.dimidroid.beerscatalog.util.Resource
import okhttp3.internal.notifyAll

//NOT WORKING
class BeersSearchFragment: Fragment() {

    private lateinit var viewModel: BeersSearchViewModel
    lateinit var beersAdapter: BeersSearchAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var searchView: SearchView
    var list: List<BeerResponseItem> = ArrayList()
    val TAG = "SearchCatalogFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search_beers, container, false)
        setUIElements(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = BeersRepository(BeersDatabase(requireContext()))
        val viewModelProviderFactory = BeersSearchViewModelProvider(repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[BeersSearchViewModel::class.java]

        setupRecyclerView()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                beersAdapter.filter.filter(newText)
                return true
            }

        })

        viewModel.searchCraftBeer.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success ->{
                    response.data?.let { beersResponse ->
                        //beersAdapter.differ.submitList(beersResponse)
                        beersAdapter.filteredBeersCatalog.notifyAll()
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e(TAG, "An error occurred: $message")
                    }
                }
                else -> {}
            }
        })

    }

    private fun setUIElements(view: View){
        recyclerView = view.findViewById(R.id.recyclerViewSearch)
        searchView = view.findViewById(R.id.searchViewSearch)
        searchView.queryHint = "Search for Beer"
    }

    private fun setupRecyclerView(){
        beersAdapter = BeersSearchAdapter(list)
        recyclerView.apply {
            adapter = beersAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}