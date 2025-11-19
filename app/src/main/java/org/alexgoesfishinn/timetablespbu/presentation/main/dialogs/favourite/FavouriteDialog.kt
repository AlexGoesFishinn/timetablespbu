package org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.favourite

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.alexgoesfishinn.timetablespbu.NavGraphDirections
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.presentation.main.adapters.FavouriteAdapter
import org.alexgoesfishinn.timetablespbu.presentation.main.adapters.FavouriteNavigateClickListener
import org.alexgoesfishinn.timetablespbu.presentation.main.adapters.FavouriteRemoveClickListener
import androidx.core.graphics.drawable.toDrawable

/**
 * @author a.bylev
 */
@AndroidEntryPoint
class FavouriteDialog: DialogFragment(R.layout.favourite_dialog) {
    private val viewModel: FavouriteDialogViewModel by viewModels()
    private lateinit var favouriteRecycler: RecyclerView
    private lateinit var favouriteAdapter: FavouriteAdapter
    private lateinit var favouriteIsEmptyText: TextView
    private lateinit var favouriteCloseButton: MaterialButton




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favouriteRecycler = view.findViewById(R.id.edit_favourite_recycler)
        favouriteIsEmptyText = view.findViewById(R.id.favourite_is_empty_text)
        favouriteCloseButton = view.findViewById(R.id.favourite_dialog_close_button)
        favouriteCloseButton.setOnClickListener {
            dismiss()
        }
        initFavouriteRecycler()
        subscribeFavourite()
    }


    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
//        dialog?.window?.setLayout(
//            resources.displayMetrics.widthPixels * 9 / 10,
//            resources.displayMetrics.heightPixels * 9 / 10
//        )
    }




    private fun initFavouriteRecycler(){
        favouriteAdapter = FavouriteAdapter(object : FavouriteNavigateClickListener {
            override fun onClick(id: Long) {
                val actionToEvents = NavGraphDirections.toEvents(id)
                findNavController().navigate(actionToEvents)
                dismiss()

            }
        }, object : FavouriteRemoveClickListener {
            override fun onClick(id: Long) {
                viewModel.removeFromFavourite(id)

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
                } else{ favouriteIsEmptyText.visibility = View.VISIBLE
                }
                favouriteAdapter.data = it
            }
        }
    }
}