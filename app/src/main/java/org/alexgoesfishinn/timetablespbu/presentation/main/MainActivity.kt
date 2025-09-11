package org.alexgoesfishinn.timetablespbu.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.alexgoesfishinn.timetablespbu.NavGraphDirections
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.domain.DataStoreManager
import javax.inject.Inject


/**
 * @author a.bylev
 */
@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
private lateinit var navigation: NavController
@Inject lateinit var dataStoreManager: DataStoreManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navigation = navHostFragment.findNavController()
        initBottomNavigation()

    }

    fun initBottomNavigation(){
        val navigationToMain = findViewById<CardView>(R.id.bottom_navigation_to_main)
        navigationToMain.setOnClickListener {
            Log.i(TAG, "На главную")
            navigation.navigate(R.id.toDivisions)
        }
        val navigationToEvents = findViewById<CardView>(R.id.bottom_navigation_to_events)
        val navigationToEventsText = findViewById<TextView>(R.id.bottom_navigation_to_events_text)
        lifecycleScope.launch {
            val groupId = async { dataStoreManager.getGroupId() }.await()
            val groupName = async { dataStoreManager.getGroupName() }.await()
            if(groupId == ""){
                navigationToEvents.visibility = View.GONE
            }
            else{
                navigationToEventsText.text = groupName
                navigationToEvents.visibility = View.VISIBLE
                navigationToEvents.setOnClickListener {
                    Log.i(TAG, "В избранное groupId = $groupId, groupName = $groupName")
                    val actionToEvents = NavGraphDirections.toEvents(groupId, groupName)
                    navigation.navigate(actionToEvents)
                }
            }
        }
    }

    companion object{
        private const val TAG = "Main Activity"
    }

    }