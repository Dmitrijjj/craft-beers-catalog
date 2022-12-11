package com.dimidroid.beerscatalog.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dimidroid.beerscatalog.R
import com.dimidroid.beerscatalog.databinding.ActivityBeersBinding
import com.dimidroid.beerscatalog.db.BeersDatabase
import com.dimidroid.beerscatalog.repository.BeersRepository

class BeersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBeersBinding
    lateinit var viewModel: BeersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = BeersRepository(BeersDatabase(this))
        val viewModelProviderFactory = BeersViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[BeersViewModel::class.java]

        val navView: BottomNavigationView = binding.navView

        val navigationHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_beers) as NavHostFragment

        val navController = navigationHost.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_beers_catalog, R.id.navigation_favourite_beers
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}