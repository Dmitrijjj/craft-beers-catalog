package com.dimidroid.beerscatalog.ui.beers_catalog

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dimidroid.beerscatalog.R
import com.dimidroid.beerscatalog.adapters.BeersAdapter
import com.dimidroid.beerscatalog.db.BeersDatabase
import com.dimidroid.beerscatalog.repository.BeersRepository
import com.dimidroid.beerscatalog.util.Resource

class BeersCatalogFragment : Fragment() {

    lateinit var viewModel: BeersCatalogViewModel
    lateinit var adapter: BeersAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var searchView: SearchView
    val TAG = "BeersCatalogFragment"

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
        val viewModelProviderFactory = CatalogViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[BeersCatalogViewModel::class.java]

        recyclerView = view.findViewById(R.id.recyclerViewCatalog)
        progressBar = view.findViewById(R.id.paginationProgressBar)
        searchView = view.findViewById(R.id.searchView)
        setupRecyclerView()

        viewModel.craftBeer.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success ->{
                    hideProgressBar()
                    response.data?.let { beersResponse ->
                        adapter.differ.submitList(beersResponse)
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
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        adapter = BeersAdapter()
        recyclerView.apply {
            adapter = adapter
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }
    }


}