package org.alexgoesfishinn.timetablespbu.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.alexgoesfishinn.timetablespbu.data.network.utils.WarningsNotificator
import org.alexgoesfishinn.timetablespbu.domain.usecases.DeleteOldEventsUseCase
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val warningsNotificator: WarningsNotificator,
    private val deleteOldEventsUseCase: DeleteOldEventsUseCase
): ViewModel() {
    private val _internetIsNotAvailable: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val internetIsNotAvailable: StateFlow<Boolean> = _internetIsNotAvailable.asStateFlow()
    private val _apiError: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val apiError: StateFlow<Boolean> = _apiError.asStateFlow()
    private val _groupIsNotAvailable: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val groupIsNotAvailable: StateFlow<Boolean> = _groupIsNotAvailable.asStateFlow()
    private val _serverTimeout: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val serverTimeout: StateFlow<Boolean> = _serverTimeout.asStateFlow()

    init {
        subscribeInternetIsNotAvailable()
        subscribeApiError()
        subscribeGroupIsNotAvailable()
        subscribeServerTimeout()
        deleteOldEvents()

    }

    fun internetIsNotAvailableNotified(){
        warningsNotificator.internetIsNotAvailableNotifyOff()
    }

    fun serverTimeoutNotified(){
        warningsNotificator.serverTimeoutNotifyOff()
    }

    fun apiErrorNotified(){
        warningsNotificator.apiErrorNotifyOff()
    }

    fun groupIsNotAvailableNotified(){
        warningsNotificator.groupIsNotAvailableNotifyOff()
    }

    private fun subscribeServerTimeout(){
        viewModelScope.launch {
            warningsNotificator.serverTimeout.collect {
                _serverTimeout.value = it
            }
        }
    }

    private fun deleteOldEvents(){
        viewModelScope.launch {
            deleteOldEventsUseCase.deleteEventsByUnusedGroup()
            deleteOldEventsUseCase.deleteEventsByDate()
        }
    }

    private fun subscribeInternetIsNotAvailable(){
        viewModelScope.launch {
            warningsNotificator.internetIsNotAvailable.collect{
                Log.i("ActivityViewModel", "internetIsNotAvailable = $it")
                _internetIsNotAvailable.value = it
            }
        }
    }

    private fun subscribeApiError(){
        viewModelScope.launch {
            warningsNotificator.apiError.collect {
                _apiError.value = it
            }
        }
    }

    private fun subscribeGroupIsNotAvailable(){
        viewModelScope.launch {
            warningsNotificator.groupIsNotAvailable.collect {
                _groupIsNotAvailable.value = it
            }
        }
    }
}