package org.alexgoesfishinn.timetablespbu.presentation.main

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.domain.entities.EventLocation
import org.alexgoesfishinn.timetablespbu.presentation.main.adapter.EventLocationAdapter

class EventLocationsDialog(
    private var context: Context,
    private var eventLocations: List<EventLocation>
): Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootLayout: ConstraintLayout = findViewById(R.id.eventLocationsLayout)
        val view = LayoutInflater.from(context).inflate(R.layout.eventlocations_dialog, rootLayout, false)
        setContentView(view)
        setCanceledOnTouchOutside(true)
        setCancelable(true)
        setUpRecyclerView(view)
    }
    private fun setUpRecyclerView(view: View){
        val eventLocationRecycler: RecyclerView = view.findViewById(R.id.eventLocationRecycler)
        eventLocationRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = EventLocationAdapter(eventLocations)
        }
    }
}