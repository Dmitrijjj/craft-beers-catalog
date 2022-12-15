package com.dimidroid.beerscatalog.ui.beers_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.dimidroid.beerscatalog.R


class BeersDetailsFragment : Fragment() {

    private val args: BeersDetailsFragmentArgs by navArgs()
    lateinit var description: TextView
    lateinit var ingredients: TextView
    lateinit var foodPairing: TextView

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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setValues()
    }

    private fun setValues() {
        val beerItem = args.beerInfo
        description.text = beerItem?.description.toString()
        ingredients.text = beerItem?.ingredients.toString()
        foodPairing.text = beerItem?.foodPairing.toString()
    }

}