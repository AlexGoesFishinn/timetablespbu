package org.alexgoesfishinn.timetablespbu.presentation.main.fragments.events

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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