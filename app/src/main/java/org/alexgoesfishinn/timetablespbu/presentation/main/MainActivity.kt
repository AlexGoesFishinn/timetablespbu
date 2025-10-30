package org.alexgoesfishinn.timetablespbu.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
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
import org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.clearcache.ClearCacheDialog
import org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.favourite.FavouriteDialog
import org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.groupisnotavailable.GroupIsNotAvailableDialog
import org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.nointernet.NoInternetDialog
import org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.servertimeout.ServerTimeoutErrorDialog
import org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.somethingwentwrong.SomethingWentWrongDialog
import javax.inject.Inject


/**
 * @author a.bylev
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navigation: NavController
    private val viewModel by viewModels<MainActivityViewModel>()

    @Inject
    lateinit var noInternetDialog: NoInternetDialog
    @Inject
    lateinit var somethingWentWrongDialog: SomethingWentWrongDialog
    @Inject
    lateinit var groupIsNotAvailableDialog: GroupIsNotAvailableDialog
    @Inject
    lateinit var serverTimeoutErrorDialog: ServerTimeoutErrorDialog
    @Inject
    lateinit var clearCacheDialog: ClearCacheDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navigation = navHostFragment.findNavController()
        initBottomNavigation()
        subscribeInternetChecker()
        subscribeApiError()
        subscribeGroupIsNotAvailable()
        subscribeServerTimeout()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menu?.add(R.string.clear_cache)
            ?.setOnMenuItemClickListener {
            clearCacheDialog.show(supportFragmentManager, "ClearCacheDialog")
            true
        }

        return true
    }


    private fun subscribeServerTimeout() {
        lifecycleScope.launch {
            viewModel.serverTimeout.collect {
                if (it) {
                    serverTimeoutErrorDialog.show(
                        supportFragmentManager,
                        "Server timeout exception"
                    )
                }
            }
        }
    }

    private fun subscribeInternetChecker() {
        lifecycleScope.launch {
            viewModel.internetIsNotAvailable.collect {
                Log.i("MainActivity", "$it")
                if (it) {
                    noInternetDialog.show(supportFragmentManager, "Internet is not available")

                }
            }
        }
    }

    private fun subscribeGroupIsNotAvailable() {
        lifecycleScope.launch {
            viewModel.groupIsNotAvailable.collect {
                if (it) {
                    groupIsNotAvailableDialog.show(supportFragmentManager, "Group is not available")
                }
            }
        }
    }

    private fun subscribeApiError() {
        lifecycleScope.launch {
            viewModel.apiError.collect {
                if (it) {
                    somethingWentWrongDialog.show(supportFragmentManager, "Something went wrong")
                }
            }
        }
    }


    private fun initBottomNavigation() {
        val navigationToMain = findViewById<CardView>(R.id.bottom_navigation_to_main)
        navigationToMain.setOnClickListener {
            navigation.navigate(R.id.to_divisions)
        }
        val favouriteButton = findViewById<CardView>(R.id.bottom_navigation_favourite)
        favouriteButton.setOnClickListener {
            FavouriteDialog().show(supportFragmentManager, "FavoriteDialog")

        }

    }


}