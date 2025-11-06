package org.alexgoesfishinn.timetablespbu.presentation.main.fragments.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView
import org.alexgoesfishinn.timetablespbu.R

class MainFragment: Fragment(R.layout.main_fragment) {
    private lateinit var chooseGroupButton: MaterialCardView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chooseGroupButton = view.findViewById(R.id.choose_group_button)
        initChooseGroupButton()

    }
    private fun initChooseGroupButton(){
        chooseGroupButton.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.toDivisions())
        }
    }
}