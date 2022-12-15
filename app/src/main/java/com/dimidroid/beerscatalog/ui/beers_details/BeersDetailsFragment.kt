package com.dimidroid.beerscatalog.ui.beers_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.dimidroid.beerscatalog.R
import com.dimidroid.beerscatalog.db.BeersDatabase
import com.dimidroid.beerscatalog.repository.BeersRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BeersDetailsFragment : Fragment() {

    private lateinit var viewModel: BeersDetailsViewModel
    private val args: BeersDetailsFragmentArgs by navArgs()
    lateinit var description: TextView
    lateinit var ingredients: TextView
    lateinit var foodPairing: TextView
    lateinit var addBtn: FloatingActionButton
    var isFav = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_beers_details, container, false)
        setUIElements(view)
        return view
    }

    private fun setUIElements(view: View) {
        description = view.findViewById(R.id.beer_info_tv_description)
        ingredients = view.findViewById(R.id.beer_info_tv_ingredients)
        foodPairing = view.findViewById(R.id.beer_info_tv_foodPairing)
        addBtn = view.findViewById(R.id.fab)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = BeersRepository(BeersDatabase(requireContext()))
        val viewModelProviderFactory = BeersDetailsViewModelProvider(repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[BeersDetailsViewModel::class.java]

        val beerItem = args.beerInfo
        description.text = beerItem?.description.toString()
        ingredients.text = beerItem?.ingredients.toString()
        foodPairing.text = "Food pairing: " + beerItem?.foodPairing.toString()

        addBtn.setOnClickListener {
            isFav = true
            if (beerItem != null) {
                viewModel.saveBeer(beerItem)
                Toast.makeText(requireContext(),"Item added to Favourites!", Toast.LENGTH_SHORT).show()
            }
        }
    }

}