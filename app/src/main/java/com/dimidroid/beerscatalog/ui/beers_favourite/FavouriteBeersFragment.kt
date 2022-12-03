package com.dimidroid.beerscatalog.ui.beers_favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dimidroid.beerscatalog.databinding.FragmentFavouriteBeersBinding

class FavouriteBeersFragment : Fragment() {

    private var _binding: FragmentFavouriteBeersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val beersFavouriteViewModel =
            ViewModelProvider(this).get(FavouriteBeersViewModel::class.java)

        _binding = FragmentFavouriteBeersBinding.inflate(inflater, container, false)
        val root: View = binding.root


        beersFavouriteViewModel.text.observe(viewLifecycleOwner) {

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}