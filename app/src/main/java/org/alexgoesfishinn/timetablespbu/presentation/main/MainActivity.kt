package org.alexgoesfishinn.timetablespbu.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
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
    private lateinit var loadingLayout: RelativeLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navigation = navHostFragment.findNavController()
        enableEdgeToEdge()
        val activityLayout: ConstraintLayout = findViewById(R.id.main_activity_layout)
        ViewCompat.setOnApplyWindowInsetsListener(activityLayout) {v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                leftMargin = insets.left
                rightMargin = insets.right
                bottomMargin = insets.bottom
                topMargin = insets.top
            }
            WindowInsetsCompat.CONSUMED
        }
//        val bottomNavigation: ConstraintLayout = findViewById(R.id.bottom_navigation)
//        ViewCompat.setOnApplyWindowInsetsListener(bottomNavigation) { v, windowInsets ->
//            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
//            // Apply the insets as a margin to the view. This solution sets
//            // only the bottom, left, and right dimensions, but you can apply whichever
//            // insets are appropriate to your layout. You can also update the view padding
//            // if that's more appropriate.
//            v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
//                leftMargin = insets.left
//                bottomMargin = insets.bottom
//                rightMargin = insets.right
//            }
//
//            // Return CONSUMED if you don't want the window insets to keep passing
//            // down to descendant views.
//            WindowInsetsCompat.CONSUMED
//        }

        customizeBackButton()
        initBottomNavigation()
        subscribeInternetChecker()
        subscribeApiError()
        subscribeGroupIsNotAvailable()
        subscribeServerTimeout()
        subscribeLoadingNotifications()
        initAnimation()
        hideFavouriteButton()
    }

    private fun hideFavouriteButton(){
        val favouriteButton = findViewById<CardView>(R.id.bottom_navigation_favourite)
        favouriteButton.visibility = View.GONE
    }

    private fun customizeBackButton(){
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.loadingFinished()
                isEnabled = false
                onBackPressedDispatcher.onBackPressed()
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menu?.add(getString(R.string.about))?.setOnMenuItemClickListener {
            navigation.navigate(R.id.to_about)
            true
        }
        menu?.add(R.string.clear_cache)
            ?.setOnMenuItemClickListener {
            clearCacheDialog.show(supportFragmentManager, "ClearCacheDialog")
            true
        }
        return true
    }

    private fun subscribeLoadingNotifications(){
        loadingLayout = findViewById(R.id.loading_layout)
        lifecycleScope.launch {
            viewModel.isLoading.collect {
                if(it){
                    isLoading()
                } else {
                    loadingFinished()
                }
            }
        }
    }

    private fun isLoading(){
        loadingLayout.visibility = View.VISIBLE
    }

    private fun loadingFinished(){
        loadingLayout.visibility = View.GONE
    }

    private fun initAnimation(){
        val loadingImage: ImageView = findViewById(R.id.loading_icon)
//        val loadingText: TextView = findViewById(R.id.loading_text)
        val animation = AlphaAnimation(0f, 1f).apply {
            duration = 1000L
            repeatMode = Animation.REVERSE
            repeatCount = Animation.INFINITE
            fillAfter = true
        }
        loadingImage.startAnimation(animation)
//        loadingText.startAnimation(animation)
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
            viewModel.loadingFinished()
            navigation.navigate(R.id.to_main)
        }
        val favouriteButton = findViewById<CardView>(R.id.bottom_navigation_favourite)
        favouriteButton.setOnClickListener {
            FavouriteDialog().show(supportFragmentManager, "FavoriteDialog")
        }
    }
}