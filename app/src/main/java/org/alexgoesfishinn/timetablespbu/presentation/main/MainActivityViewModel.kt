package org.alexgoesfishinn.timetablespbu.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.alexgoesfishinn.timetablespbu.data.network.utils.WarningsNotificator
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val warningsNotificator: WarningsNotificator
): ViewModel() {
    private val _internetIsNotAvailable: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val internetIsNotAvailable: StateFlow<Boolean> = _internetIsNotAvailable.asStateFlow()
    private val _apiError: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val apiError: StateFlow<Boolean> = _apiError.asStateFlow()
    private val _groupIsNotAvailable: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val groupIsNotAvailable: StateFlow<Boolean> = _groupIsNotAvailable.asStateFlow()

    init {
        subscribeInternetIsNotAvailable()
        subscribeApiError()
        subscribeGroupIsNotAvailable()
    }

    private fun subscribeInternetIsNotAvailable(){
        viewModelScope.launch {
            warningsNotificator.internetIsNotAvailable.collect{
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