package org.alexgoesfishinn.timetablespbu.presentation.main

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.WindowMetrics
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

//    override fun onStart() {
//        super.onStart()
//        window?.attributes?.width = WindowManager.LayoutParams.MATCH_PARENT
//        window?.attributes?.height = WindowManager.LayoutParams.WRAP_CONTENT
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val rootLayout: ConstraintLayout = findViewById(R.id.eventLocationsLayout)
        val view = LayoutInflater.from(context).inflate(R.layout.eventlocations_dialog, null, false)
        setUpRecyclerView(view)
        Log.i("Dialog", eventLocations.toString())
        setContentView(view)
        setCanceledOnTouchOutside(true)
        setCancelable(true)

    }
    private fun setUpRecyclerView(view: View){
        val eventLocationRecycler: RecyclerView = view.findViewById(R.id.eventLocationRecycler)
        eventLocationRecycler.apply {
            Log.i("EventLocationDialog", "setupRecycler")
            layoutManager = LinearLayoutManager(context)
            adapter = EventLocationAdapter(eventLocations)
        }

    }
}