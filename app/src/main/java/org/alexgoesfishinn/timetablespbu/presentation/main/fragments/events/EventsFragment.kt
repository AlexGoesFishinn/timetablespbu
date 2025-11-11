package org.alexgoesfishinn.timetablespbu.presentation.main.fragments.events

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
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
    private lateinit var addToFavouriteCard: MaterialCardView
    private lateinit var groupNameText: TextView
    private lateinit var activity: MainActivity
    private lateinit var previousWeekMondayString: String
    private lateinit var nextWeekMondayString: String
    private lateinit var layout: ConstraintLayout


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity = requireActivity() as MainActivity
        binding = EventsFragmentBinding.bind(view)
        noEventsText = view.findViewById(R.id.no_events_text)
        weekEventsNavPanelView = view.findViewById(R.id.week_events_nav_panel)
        groupNameText = view.findViewById(R.id.group_name_text)
        addToFavouriteText = view.findViewById(R.id.add_to_favourite_text)
        addToFavouriteCard = view.findViewById(R.id.add_to_favourite_card)
        weekDisplayTextView = view.findViewById(R.id.current_week_text)
        nextWeekButton = view.findViewById(R.id.next_week_button)
        previousWeekButton = view.findViewById(R.id.previous_week_button)
        eventsRecycler = view.findViewById(R.id.events_recycler)
        daysRecycler = view.findViewById(R.id.days_recycler)
        layout = view.findViewById(R.id.events_fragment_layout)
        playAnimation(layout)


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


    private fun subscribeToFavourite() {
        lifecycleScope.launch {
            viewmodel.isFavourite.collect {
                val isFavourite = it
                Log.i(TAG, "Favourite = $isFavourite")
                if (isFavourite) {
                    addToFavouriteText.text = getString(R.string.remove_from_favourite)
                    addToFavouriteCard.setOnClickListener {
                        viewmodel.removeFromFavourite()

                    }
                } else {
                    addToFavouriteText.text = getString(R.string.add_to_favourite)
                    addToFavouriteCard.setOnClickListener {
                        viewmodel.addToFavourite()
                    }
                }

            }
        }
    }

    private fun playAnimation(view: View) {
        val animation = AlphaAnimation(0f, 1f).apply {
            duration = 500L
            fillAfter = true
        }
        view.startAnimation(animation)
    }

    private fun subscribeToGroupName() {
        lifecycleScope.launch {
            viewmodel.groupDisplayName.collect {
                if (it == "") {
                    addToFavouriteCard.visibility = View.GONE
                } else {
                    addToFavouriteCard.visibility = View.VISIBLE
                }
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
//                    playFadeoutAnimation(daysRecycler)
//                    playFadeoutAnimation(eventsRecycler)
                }
                previousWeekButton.setOnClickListener {
                    viewmodel.getWeek(previousWeekMondayString)
//                    playFadeoutAnimation(daysRecycler)
//                    playFadeoutAnimation(eventsRecycler)
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
//                playFadeoutAnimation(eventsRecycler)

            }
        })
        daysRecycler.adapter = daysAdapter
        daysRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

//    private fun playFadeoutAnimation(view: View){
//        val animation = AlphaAnimation(1f,0f).apply {
//            duration = 500L
//            fillAfter = true
//        }
//        view.startAnimation(animation)
//    }

    private fun subscribeToDays() {
        lifecycleScope.launch {
            viewmodel.days.collect {
                daysAdapter.data = it
                if (it.isEmpty()) {
                    eventsRecycler.visibility = View.GONE
                } else {
                    eventsRecycler.visibility = View.VISIBLE
                    playAnimation(daysRecycler)
                }
            }
        }
    }

    private fun subscribeToEvents() {
        lifecycleScope.launch {
            viewmodel.events.collect {
                refreshEventsAdapter()
                eventsAdapter.data = it
                if(it.isNotEmpty()){
                    playAnimation(eventsRecycler)
                }
                Log.i(TAG, "events = $it")
            }
        }
    }

    private fun refreshEventsAdapter(){
        eventsRecycler.adapter = eventsAdapter
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
                if (it >= 0) {
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