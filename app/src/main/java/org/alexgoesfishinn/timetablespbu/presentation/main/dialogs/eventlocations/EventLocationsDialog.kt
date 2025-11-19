package org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.eventlocations

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.presentation.main.adapters.EventLocationAdapter
import org.alexgoesfishinn.timetablespbu.presentation.main.model.LocationItem

/**
 * @author a.bylev
 */
class EventLocationsDialog(
    private var context: Context,
    private var eventLocations: List<LocationItem>
): Dialog(context) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = LayoutInflater.from(context).inflate(R.layout.eventlocations_dialog, null, false)
        setUpRecyclerView(view)
        Log.i("Dialog", eventLocations.toString())
        setContentView(view)
        setCanceledOnTouchOutside(true)
        setCancelable(true)
        val button: MaterialCardView = findViewById(R.id.event_locations_dialog_close_button)
        button.setOnClickListener {
            dismiss()
        }

    }

    override fun onStart() {
        super.onStart()
        window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
    }

    private fun setUpRecyclerView(view: View){
        val eventLocationRecycler: RecyclerView = view.findViewById(R.id.event_locations_recycler)
        eventLocationRecycler.apply {
            Log.i("EventLocationDialog", "setupRecycler")
            layoutManager = LinearLayoutManager(context)
            adapter = EventLocationAdapter(eventLocations)
        }

    }


}