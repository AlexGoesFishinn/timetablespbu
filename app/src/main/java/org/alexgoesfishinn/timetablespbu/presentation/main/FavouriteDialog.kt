package org.alexgoesfishinn.timetablespbu.presentation.main

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.alexgoesfishinn.timetablespbu.NavGraphDirections
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.presentation.main.adapters.FavouriteAdapter
import org.alexgoesfishinn.timetablespbu.presentation.main.adapters.FavouriteClickListener
@AndroidEntryPoint
class FavouriteDialog: DialogFragment(R.layout.favourite_dialog) {
    private val viewModel: FavouriteDialogViewModel by viewModels()
    private lateinit var favouriteRecycler: RecyclerView
    private lateinit var favouriteAdapter: FavouriteAdapter
    private lateinit var favouriteIsEmptyText: TextView

//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return super.onCreateDialog(savedInstanceState)
//    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favouriteRecycler = view.findViewById(R.id.favourite_recycler)
        favouriteIsEmptyText = view.findViewById(R.id.favourite_is_emty_text)

        initFavouriteRecycler()
        subscribeFavourite()
    }

//    override fun onResume() {
//        val window = dialog!!.window
//        val display = requireContext().display
//
//        super.onResume()
//    }




    private fun initFavouriteRecycler(){
        favouriteAdapter = FavouriteAdapter(object : FavouriteClickListener {
            override fun onClick(id: Long) {
                val actionToEvents = NavGraphDirections.toEvents(id)
                findNavController().navigate(actionToEvents)
//                TODO("Not yet implemented")
            }
        })
        favouriteRecycler.adapter = favouriteAdapter
        favouriteRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun subscribeFavourite(){
        lifecycleScope.launch {
            viewModel.favourite.collect {
                if(it.isNotEmpty()){
                    favouriteIsEmptyText.visibility = View.GONE
                } else{ favouriteIsEmptyText.visibility = View.VISIBLE}
                favouriteAdapter.data = it
            }
        }
    }
}