package com.dimidroid.beerscatalog.ui.beers_search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dimidroid.beerscatalog.R
import com.dimidroid.beerscatalog.adapters.BeersCatalogAdapter
import com.dimidroid.beerscatalog.db.BeersDatabase
import com.dimidroid.beerscatalog.repository.BeersRepository
import com.dimidroid.beerscatalog.util.Resource

class BeersSearchFragment: Fragment() {

    private lateinit var viewModel: BeersSearchViewModel
    lateinit var beersAdapter: BeersCatalogAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var searchView: SearchView
    lateinit var progressBar: ProgressBar
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

        beersAdapter.setOnClickListener {
            val direction = BeersSearchFragmentDirections.actionBeersSearchFragmentToBeersDetailsFragment(it)
            findNavController().navigate(direction)
        }

        viewModel.searchCraftBeer.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success ->{
                    hideProgressBar()
                    response.data?.let { beersResponse ->
                        beersAdapter.differ.submitList(beersResponse)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occurred: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchForBeers(newText)
                return true
            }

        })

    }

    private fun setUIElements(view: View){
        recyclerView = view.findViewById(R.id.recyclerViewSearch)
        searchView = view.findViewById(R.id.searchViewSearch)
        searchView.queryHint = "Search for Beer"
        progressBar = view.findViewById(R.id.paginationProgressBarSearch)
    }

    private fun setupRecyclerView(){
        beersAdapter = BeersCatalogAdapter()
        recyclerView.apply {
            adapter = beersAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }
}