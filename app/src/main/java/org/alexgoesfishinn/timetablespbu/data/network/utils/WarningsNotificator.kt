package org.alexgoesfishinn.timetablespbu.data.network.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WarningsNotificator {

    private val _internetIsNotAvailable: MutableStateFlow<Boolean> = MutableStateFlow(value = false)
    val internetIsNotAvailable: StateFlow<Boolean> = _internetIsNotAvailable.asStateFlow()
    private val _apiError: MutableStateFlow<Boolean> = MutableStateFlow(value = false)
    val apiError: StateFlow<Boolean> = _apiError.asStateFlow()
    private val _groupIsNotAvailable: MutableStateFlow<Boolean> = MutableStateFlow(value = false)
    val groupIsNotAvailable: StateFlow<Boolean> = _groupIsNotAvailable.asStateFlow()

    fun internetIsNotAvailableNotify(){
        _internetIsNotAvailable.value = true
        _internetIsNotAvailable.value = false
    }

    fun apiErrorNotify(){
        _apiError.value = true
        _apiError.value = false
    }

    fun groupIsNotAvailableNotify(){
        _groupIsNotAvailable.value = true
        _groupIsNotAvailable.value = false
    }
}