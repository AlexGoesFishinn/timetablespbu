package org.alexgoesfishinn.timetablespbu.presentation.main.fragments.events

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.databinding.EventsFragmentBinding
import org.alexgoesfishinn.timetablespbu.presentation.main.MainActivity
import org.alexgoesfishinn.timetablespbu.presentation.main.adapters.DaysAdapter
import org.alexgoesfishinn.timetablespbu.presentation.main.adapters.DaysClickListener
import org.alexgoesfishinn.timetablespbu.presentation.main.adapters.EventsAdapter


/**
 * @author a.bylev
 */
@AndroidEntryPoint
class EventsFragment : Fragment(R.layout.events_fragment) {
    private var binding: EventsFragmentBinding? = null
    private val viewmodel by viewModels<EventsViewModel>()
    private lateinit var noEventsText: TextView
    private lateinit var weekEventsNavPanelView: View
    private lateinit var weekDisplayTextView: TextView
    private lateinit var previousWeekButton: TextView
    private lateinit var nextWeekButton: TextView
    private lateinit var daysRecycler: RecyclerView
    private lateinit var daysAdapter: DaysAdapter
    private lateinit var eventsRecycler: RecyclerView
    private lateinit var eventsAdapter: EventsAdapter
    private lateinit var addToFavouriteText: TextView
    private lateinit var addToFavoriteCard: MaterialCardView
    private lateinit var groupNameText: TextView
    private lateinit var activity: MainActivity
    private lateinit var previousWeekMondayString: String
    private lateinit var nextWeekMondayString: String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity = requireActivity() as MainActivity
        binding = EventsFragmentBinding.bind(view)
        noEventsText = view.findViewById(R.id.noEventsText)
        weekEventsNavPanelView = view.findViewById(R.id.weekEventsNavPanel)
        groupNameText = view.findViewById(R.id.groupNameText)
        addToFavouriteText = view.findViewById(R.id.addToFavouriteText)
        addToFavoriteCard = view.findViewById(R.id.addToFavouriteCard)
        weekDisplayTextView = view.findViewById(R.id.currentWeekText)
        nextWeekButton = view.findViewById(R.id.nextWeekButton)
        previousWeekButton = view.findViewById(R.id.previousWeekButton)
        eventsRecycler = view.findViewById(R.id.eventsRecycler)
        daysRecycler = view.findViewById(R.id.daysRecycler)


        initDaysRecycler()
        initEventsRecycler()
        subscribeToDays()
        subscribePreviousMonday()
        subscribeNextMonday()
        subscribeWeekDisplayText()
        subscribeToEvents()
        initButtons()
        scrollToDay()
        subscribeToGroupName()
        subscribeToFavourite()


    }


    private fun subscribeToFavourite(){
        lifecycleScope.launch {
            viewmodel.isFavourite.collect{
                val isFavourite = it
                Log.i(TAG,"Favourite = $isFavourite")
                if(isFavourite){
                    addToFavouriteText.text = "Убрать из избранного"
                    addToFavoriteCard.setOnClickListener {
                        viewmodel.removeFromFavourite()

                    }
                } else{
                    addToFavouriteText.text = "Добавить в избранное"
                    addToFavoriteCard.setOnClickListener {
                        viewmodel.addToFavourite()
                    }
                }

            }
        }
    }

    private fun subscribeToGroupName(){
        lifecycleScope.launch {
            viewmodel.groupDisplayName.collect {
                groupNameText.text = it
            }
        }
    }

    private fun initButtons() {
        lifecycleScope.launch {
            viewmodel.groupEvents.collect {
                val groupEvents = it
                nextWeekButton.setOnClickListener {
                    viewmodel.getWeek(nextWeekMondayString)
                }
                previousWeekButton.setOnClickListener {
                    viewmodel.getWeek(previousWeekMondayString)
                }
                noEventsText.visibility = View.VISIBLE
                if (groupEvents != null && groupEvents.days.isNotEmpty()) {
                    noEventsText.visibility = View.GONE
                }

            }
        }
    }



    private fun initEventsRecycler() {
        eventsAdapter = EventsAdapter()
        eventsRecycler.adapter = eventsAdapter
        eventsRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initDaysRecycler() {
        daysAdapter = DaysAdapter(object : DaysClickListener {
            override fun onItemClick(dayId: Long, adapterPosition: Int) {
                viewmodel.setAdapterPosition(adapterPosition)
                Log.i("EventsFragment", "onClick adapterPosition = $adapterPosition")
                viewmodel.getEvents(dayId)

            }
        })
        daysRecycler.adapter = daysAdapter
        daysRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun subscribeToDays() {
        lifecycleScope.launch {
            viewmodel.days.collect {
                daysAdapter.data = it
                if(it.isEmpty()){
                    eventsRecycler.visibility = View.GONE
                } else eventsRecycler.visibility = View.VISIBLE
            }
        }
    }

    private fun subscribeToEvents(){
        lifecycleScope.launch {
            viewmodel.events.collect {
                eventsAdapter.data = it
                Log.i(TAG, "events = $it")
            }
        }
    }

    private fun subscribePreviousMonday() {
        lifecycleScope.launch {
            viewmodel.previousWeekMondayString.collect {
                previousWeekMondayString = it
            }
        }
    }

    private fun subscribeNextMonday() {
        lifecycleScope.launch {
            viewmodel.nextWeekMondayString.collect {
                nextWeekMondayString = it
            }
        }
    }

    private fun subscribeWeekDisplayText() {
        lifecycleScope.launch {
            viewmodel.generatedWeekDisplayText.collect {
                weekDisplayTextView.text = it
            }
        }
    }

    private fun scrollToDay() {
        lifecycleScope.launch {
            viewmodel.daysAdapterPosition.collect {
                if(it >= 0){
                    val adapterPosition = it
                    Log.i(TAG, "scrollToDay method")
                    daysRecycler.scrollToPosition(adapterPosition)
                    Log.i(TAG, "adapter position = $adapterPosition")
                    daysRecycler.post {
                        val click =
                            daysRecycler.findViewHolderForAdapterPosition(adapterPosition)?.itemView?.performClick()
                        Log.i(TAG, "click = $click")
                    }
                }

            }
        }

    }




    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    private companion object {
        private const val TAG = "EventsFragment"
    }
}