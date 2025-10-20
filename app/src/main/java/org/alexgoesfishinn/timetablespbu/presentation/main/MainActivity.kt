package org.alexgoesfishinn.timetablespbu.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.alexgoesfishinn.timetablespbu.R


/**
 * @author a.bylev
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navigation: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navigation = navHostFragment.findNavController()
        initBottomNavigation()

    }


    private fun initBottomNavigation() {
        val navigationToMain = findViewById<CardView>(R.id.bottom_navigation_to_main)
        navigationToMain.setOnClickListener {
            navigation.navigate(R.id.toDivisions)
        }
        val favouriteButton = findViewById<CardView>(R.id.bottom_navigation_favourite)
        favouriteButton.setOnClickListener {
            FavouriteDialog().show(supportFragmentManager, "FavoriteDialog")

        }

    }


}