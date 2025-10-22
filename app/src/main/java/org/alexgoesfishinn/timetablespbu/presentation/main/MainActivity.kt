package org.alexgoesfishinn.timetablespbu.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.NoInternetDialog
import org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.SomethingWentWrongDialog


/**
 * @author a.bylev
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navigation: NavController
    private val viewModel by viewModels<MainActivityViewModel>()
    private val noInternetDialog = NoInternetDialog()
    private val somethingWentWrongDialog = SomethingWentWrongDialog()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navigation = navHostFragment.findNavController()
        initBottomNavigation()
        subscribeInternetChecker()
        subscribeApiError()

    }

    private fun subscribeInternetChecker(){
        lifecycleScope.launch {
            viewModel.isInternetAvailable.collect{
                Log.i("MainActivity", "$it")
                if(!it){
                    noInternetDialog.show(supportFragmentManager, "Internet is not available")
                }
            }
        }
    }

    private fun subscribeApiError(){
        lifecycleScope.launch {
            viewModel.apiError.collect {
                if(it){
                    somethingWentWrongDialog.show(supportFragmentManager, "Something went wrong")
                }
            }
        }
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