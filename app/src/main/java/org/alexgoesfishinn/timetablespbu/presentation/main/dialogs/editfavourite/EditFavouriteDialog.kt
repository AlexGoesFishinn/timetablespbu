package org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.editfavourite

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.isGone
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.presentation.main.adapters.EditFavouriteAdapter
import org.alexgoesfishinn.timetablespbu.presentation.main.adapters.EditFavouriteRemoveClickListener

/**
 * @author a.bylev
 */
@AndroidEntryPoint
class EditFavouriteDialog: DialogFragment(R.layout.edit_favourite_dialog) {
    private val viewModel: EditFavouriteDialogViewModel by viewModels()
    private lateinit var editFavouriteRecycler: RecyclerView
    private lateinit var editFavouriteAdapter: EditFavouriteAdapter
    private lateinit var editFavouriteCloseButton: MaterialButton
    private lateinit var editFavouriteIsEmpty: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editFavouriteRecycler = view.findViewById(R.id.edit_favourite_recycler)
        editFavouriteIsEmpty = view.findViewById(R.id.edit_favourite_is_empty_text)
        editFavouriteCloseButton = view.findViewById(R.id.edit_favourite_dialog_close_button)
        editFavouriteCloseButton.setOnClickListener { dismiss() }
        initEditFavouriteRecycler()
        subscribeFavourite()

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
    }

    private fun initEditFavouriteRecycler(){
        editFavouriteAdapter = EditFavouriteAdapter(object : EditFavouriteRemoveClickListener {
            override fun onClick(id: Long) {
                viewModel.removeFromFavourite(id)
            }
        })
        editFavouriteRecycler.layoutManager = LinearLayoutManager(requireContext())
        editFavouriteRecycler.adapter = editFavouriteAdapter
    }

    private fun subscribeFavourite(){
        lifecycleScope.launch {
            viewModel.favourite.collect{
                editFavouriteAdapter.data = it
                if(it.isNotEmpty()){
                    editFavouriteIsEmpty.visibility = View.GONE
                }
                if(it.isEmpty() && editFavouriteIsEmpty.isGone){
                    editFavouriteIsEmpty.visibility = View.VISIBLE
                    dismiss()
                }


            }
        }
    }

}