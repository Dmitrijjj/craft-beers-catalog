package com.dimidroid.beerscatalog.ui.beers_catalog

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import androidx.core.view.MenuProvider
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

class BeersCatalogFragment : Fragment(), MenuProvider{

    private lateinit var viewModel: BeersCatalogViewModel
    lateinit var beersAdapter: BeersAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    //lateinit var searchView: SearchView
    val TAG = "BeersCatalogFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_beers_catalog, container, false)
        setUIElements(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.addMenuProvider(this, viewLifecycleOwner)

        val repository = BeersRepository(BeersDatabase(requireContext()))
        val viewModelProviderFactory = CatalogViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[BeersCatalogViewModel::class.java]

        setupRecyclerView()

        viewModel.craftBeer.observe(viewLifecycleOwner, Observer { response ->
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
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.search_menu, menu)
        val item = menu.findItem(R.id.search_action);
        val searchView = item?.actionView as androidx.appcompat.widget.SearchView

        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                beersAdapter.filter.filter(newText)
                return true
            }
        })

    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }

    private fun setUIElements(view: View){
        recyclerView = view.findViewById(R.id.recyclerViewCatalog)
        progressBar = view.findViewById(R.id.paginationProgressBar)
        //searchView.queryHint = "Search for Beers"
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        beersAdapter = BeersAdapter()
        recyclerView.apply {
            adapter = beersAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}