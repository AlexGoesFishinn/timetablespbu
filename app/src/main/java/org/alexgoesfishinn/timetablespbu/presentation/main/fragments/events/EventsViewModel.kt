package org.alexgoesfishinn.timetablespbu.presentation.main.fragments.events

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeEventsUseCase
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeGroupEventsUseCase
import org.alexgoesfishinn.timetablespbu.presentation.main.mappers.EventToUiMapper
import org.alexgoesfishinn.timetablespbu.presentation.main.mappers.GroupEventsToUiMapper
import org.alexgoesfishinn.timetablespbu.presentation.main.model.DayItem
import org.alexgoesfishinn.timetablespbu.presentation.main.model.EventItem
import org.alexgoesfishinn.timetablespbu.presentation.main.model.GroupEventsItem
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField
import java.time.temporal.TemporalAdjusters
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val subscribeGroupEventsUseCase: SubscribeGroupEventsUseCase,
    private val subscribeEventsUseCase: SubscribeEventsUseCase,
    private val groupEventsToUiMapper: GroupEventsToUiMapper,
    private val eventToUiMapper: EventToUiMapper,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _groupEvents = MutableStateFlow<GroupEventsItem?>(null)
    val groupEvents: StateFlow<GroupEventsItem?> = _groupEvents.asStateFlow()
    private val groupId: Long? = savedStateHandle["group_id"]
    val groupName: String? = savedStateHandle["group_name"]
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val generatedWeekDisplayTextFormatter = DateTimeFormatter.ofPattern("dd MMMM")
    private val now: LocalDate = LocalDate.now()
    private var currentWeekMondayString: String = now.with(ChronoField.DAY_OF_WEEK, 1)
        .format(formatter)

    private var weekMondayString: String = ""

    //    private var nextWeekMondayString: String = ""
//    private var previousWeekMondayString: String = ""
    private val _nextWeekMondayString: MutableStateFlow<String> = MutableStateFlow("")
    val nextWeekMondayString: StateFlow<String> = _nextWeekMondayString.asStateFlow()
    private val _previousWeekMondayString: MutableStateFlow<String> = MutableStateFlow("")
    val previousWeekMondayString: StateFlow<String> = _previousWeekMondayString.asStateFlow()


    //    var daysAdapterPosition = 0
    private val _daysAdapterPosition: MutableStateFlow<Int> = MutableStateFlow(0)
    val daysAdapterPosition: StateFlow<Int> = _daysAdapterPosition.asStateFlow()
    private val _days: MutableStateFlow<List<DayItem>> = MutableStateFlow(emptyList())
    val days: StateFlow<List<DayItem>> = _days.asStateFlow()
    private val _generatedWeekDisplayText: MutableStateFlow<String> = MutableStateFlow("")
    val generatedWeekDisplayText: StateFlow<String> = _generatedWeekDisplayText.asStateFlow()
    private val _events: MutableStateFlow<List<EventItem>> = MutableStateFlow(emptyList())
    val events: StateFlow<List<EventItem>> = _events.asStateFlow()

    init {
        getWeek(currentWeekMondayString)
//        val monday = now.with(ChronoField.DAY_OF_WEEK, 1)
//        currentWeekMondayString = monday.format(formatter)


//        val nextMonday = monday.with(TemporalAdjusters.next(DayOfWeek.MONDAY))
//        val previousMonday = monday.with(TemporalAdjusters.previous(DayOfWeek.MONDAY))
//
//        nextWeekMondayString = nextMonday.format(formatter)
//        previousWeekMondayString = previousMonday.format(formatter)
//        weekMondayString = currentWeekMondayString

//        groupId = savedStateHandle["group_id"]

//        viewModelScope.launch {
//            val week = groupEventsToUiMapper.invoke(
//                subscribeGroupEventsUseCase.getEvents(groupId!!, currentWeekMondayString)
//            )
//            _groupEvents.value = week
//            _days.value = week.days
//            calculateAdapterPosition()
//        }
    }

    fun getWeek(weekMonday: String) {
        generateWeekDisplayText(weekMonday)
        weekMondayString = weekMonday
        val nextMonday =
            LocalDate.parse(weekMonday, formatter).with(TemporalAdjusters.next(DayOfWeek.MONDAY))
        val previousMonday = LocalDate.parse(weekMonday, formatter)
            .with(TemporalAdjusters.previous(DayOfWeek.MONDAY))
        _nextWeekMondayString.value = nextMonday.format(formatter)
        _previousWeekMondayString.value = previousMonday.format(formatter)
//        nextWeekMondayString = nextMonday.format(formatter)
//        previousWeekMondayString = previousMonday.format(formatter)
        viewModelScope.launch {
            val week = groupEventsToUiMapper.invoke(
                subscribeGroupEventsUseCase.getEvents(groupId!!, weekMonday)
            )
            _groupEvents.value = week
            _days.value = week.days
            calculateAdapterPosition()
        }
    }

    fun getEvents(dayId: Long){
        viewModelScope.launch {
            _events.value = subscribeEventsUseCase.getEvents(dayId).map { eventToUiMapper.invoke(it) }
        }
    }

    private fun generateWeekDisplayText(weekMonday: String) {

        val monday = LocalDate.parse(weekMonday, formatter)
        val sunday = monday.with(ChronoField.DAY_OF_WEEK, 7)
        val mondayString = monday.format(generatedWeekDisplayTextFormatter)
        val sundayString = sunday.format(generatedWeekDisplayTextFormatter)
        _generatedWeekDisplayText.value = "$mondayString - $sundayString"
//        Log.i("EventsViewModel", "generateWeekDisplayText weekMonday = $weekMonday")
//        Log.i("EventsViewModel", "generateWeekDisplayText result = $mondayString - $sundayString")
    }

    private fun calculateAdapterPosition() {
        _daysAdapterPosition.value = -1
        Log.i("EventsViewModel", "calculateMethod")
        if (weekMondayString == currentWeekMondayString) {
            val weekDays = days.value
            Log.i("ViewModel", "weekDays = $weekDays")
            for (i in weekDays.indices) {
                val day = LocalDate.parse(weekDays[i].dateString.substring(0, 10), formatter)
                if (day.isEqual(now) ||
                    day.isAfter(now)
                ) {
                    _daysAdapterPosition.value = i
                    Log.i("ViewModel", "daysAdapterPositionMethod = ${_daysAdapterPosition.value} ")
                    return
                }
            }
            _daysAdapterPosition.value = weekDays.size - 1
        } else {
            _daysAdapterPosition.value = 0
        }


//        if (groupEvents.isCurrentWeekReferenceAvailable) return 0
//        val days = groupEvents.days
//        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
//        val currentDate = LocalDate.now()
//        for (i in days.indices) {
//            val dayDate = LocalDate.parse(days[i].dateString.substring(0, 10), formatter)
//            if (dayDate.isEqual(currentDate) ||
//                dayDate.isAfter(currentDate)
//            ) {
//                return i
//            }
//        }
//        return days.size - 1

    }

    fun setAdapterPosition(position: Int) {
        _daysAdapterPosition.value = position
    }
}