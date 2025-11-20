package org.alexgoesfishinn.timetablespbu.presentation.main.fragments.main

import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.alexgoesfishinn.timetablespbu.NavGraphDirections
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.presentation.main.adapters.MainFavouriteAdapter
import org.alexgoesfishinn.timetablespbu.presentation.main.adapters.NavigateClickListener
import androidx.core.view.isVisible
import org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.editfavourite.EditFavouriteDialog
import org.alexgoesfishinn.timetablespbu.presentation.main.utils.Animations
import javax.inject.Inject

/**
 * @author a.bylev
 */
@AndroidEntryPoint
class MainFragment: Fragment(R.layout.main_fragment) {
    private val viewModel: MainFragmentViewModel by viewModels()
    private lateinit var chooseGroupButton: MaterialCardView
    private lateinit var layout: ConstraintLayout
    private lateinit var logoLayout: RelativeLayout
    private lateinit var favouriteCard: MaterialCardView
    private lateinit var favouriteRecycler: RecyclerView
    private lateinit var favouriteAdapter: MainFavouriteAdapter
    private lateinit var editFavourite: MaterialCardView
    @Inject
    lateinit var animations: Animations

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chooseGroupButton = view.findViewById(R.id.choose_group_button)
        layout = view.findViewById(R.id.main_fragment_layout)
        logoLayout = view.findViewById(R.id.logo_layout)
        favouriteRecycler = view.findViewById(R.id.main_fragment_favourite_card_recycler)
        favouriteCard = view.findViewById(R.id.main_fragment_favourite_card)
        favouriteCard.visibility = View.INVISIBLE
        editFavourite = view.findViewById(R.id.main_fragment_favourite_card_edit_button)
        animations.fadeIn(layout)
        initFavouriteRecycler()
        subscribeFavourite()
        hideOfficialSpbuLogo()
//        layoutFadeIn()
        initChooseGroupButton()
        initEditFavouriteButton()
    }
    private fun initEditFavouriteButton(){
        editFavourite.setOnClickListener {
            EditFavouriteDialog().show(childFragmentManager, "EditFavouriteDialog")
//            FavouriteDialog().show(childFragmentManager, "FavoriteDialog")
        }
    }

    private fun initFavouriteRecycler(){
        favouriteRecycler.layoutManager = LinearLayoutManager(requireContext())
        favouriteAdapter = MainFavouriteAdapter(object : NavigateClickListener {
            override fun onClick(id: Long) {
                val action = NavGraphDirections.toEvents(id)
                findNavController().navigate(action)
            }
        })
        favouriteRecycler.adapter = favouriteAdapter
    }

    private fun subscribeFavourite(){
        lifecycleScope.launch {
            viewModel.favourite.collect {
                favouriteAdapter.data = it
                if(it.isNotEmpty()){
                    favouriteCard.visibility = View.VISIBLE
                }
                if(it.isEmpty()
                    && favouriteCard.isVisible
                    ){
                    animations.fadeOut(favouriteCard)
//                    fadeOutFavourite()
                }
            }
        }
    }

    private fun initChooseGroupButton() {
        chooseGroupButton.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.toDivisions())
        }
    }

    private fun hideOfficialSpbuLogo() {
        logoLayout.visibility = View.GONE
    }

//    private fun layoutFadeIn() {
////        val animation = AlphaAnimation(0f, 1f).apply {
////            duration = 500
////            fillAfter = true
////        }
////        layout.startAnimation(animation)
//        animation.fadeIn(layout)
//    }

//    private fun fadeOutFavourite(){
////        val animation = AlphaAnimation(1f, 0f).apply {
////            duration = 500L
////            fillAfter = true
////        }
////        favouriteCard.startAnimation(animation)
//        animation.fadeOut(favouriteCard)
//
//    }
}