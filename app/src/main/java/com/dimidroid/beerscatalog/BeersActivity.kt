package com.dimidroid.beerscatalog

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dimidroid.beerscatalog.databinding.ActivityBeersBinding

class BeersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBeersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navigationHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_beers) as NavHostFragment

        val navController = navigationHost.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_beers_catalog, R.id.navigation_favourite_beers,
                R.id.navigation_search_beers
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}