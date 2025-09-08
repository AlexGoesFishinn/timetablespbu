package org.alexgoesfishinn.timetablespbu.presentation.main

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import org.alexgoesfishinn.timetablespbu.R


/**
 * @author a.bylev
 */
@AndroidEntryPoint
class MainActivity: AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        if (menu != null) {
            menu.add("Главная")
            menu.add("Избранное")
        }
        return true
    }

    }