package org.alexgoesfishinn.timetablespbu.presentation.main

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
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
//        navigation = this.findNavController()
//        navigation = findNavController(R.id.nav_host_fragment)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navigation = navHostFragment.findNavController()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
//        findNavController().navigate()
        if (menu != null) {
            menu.add("Главная")
            menu.add("Избранное")
        }


        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.title == "Главная"){
            Log.i(TAG, "На главную")
            navigation.navigate(R.id.toDivisions)
            return true
        }
        if(item.title == "Избранное"){
            lifecycleScope.launch {
                val groupId = async { dataStoreManager.getGroupId() }.await()
                val groupName = async { dataStoreManager.getGroupName() }.await()
                if(groupId == ""){

                    val builder = AlertDialog.Builder(this@MainActivity)
                        .setMessage("Нет группы в избранном")
                        .setTitle("Ошибка").setPositiveButton("Ok"){dialog, id -> dialog.cancel() }
                    val dialog = builder.create()
                    dialog.show()

                } else{
                    val action = NavGraphDirections.toEvents(groupId, groupName)
                    navigation.navigate(action)
                }
            }
//            val action = NavGraphDirections.toEvents()
//            navigation.navigate(R.id.toEvents)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object{
        private const val TAG = "Main Activity"
    }

    }