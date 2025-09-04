package org.alexgoesfishinn.timetablespbu.presentation.main

import android.os.Bundle
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


}