package org.alexgoesfishinn.timetablespbu.presentation.main.fragments.main

import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView
import org.alexgoesfishinn.timetablespbu.R

class MainFragment: Fragment(R.layout.main_fragment) {
    private lateinit var chooseGroupButton: MaterialCardView
    private lateinit var layout: ConstraintLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chooseGroupButton = view.findViewById(R.id.choose_group_button)
        layout = view.findViewById(R.id.main_fragment_layout)
        initAnimation()
        initChooseGroupButton()

    }
    private fun initChooseGroupButton(){
        chooseGroupButton.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.toDivisions())
        }
    }

    private fun initAnimation(){
        val animation = AlphaAnimation(0f,1f).apply {
            duration = 500
            fillAfter = true
        }
        layout.startAnimation(animation)
    }
}