package org.alexgoesfishinn.timetablespbu.data.network.utils

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
/**
 * @author a.bylev
 */
class WarningsNotificator {

    private val _internetIsNotAvailable: MutableStateFlow<Boolean> = MutableStateFlow(value = false)
    val internetIsNotAvailable: StateFlow<Boolean> = _internetIsNotAvailable.asStateFlow()
    private val _apiError: MutableStateFlow<Boolean> = MutableStateFlow(value = false)
    val apiError: StateFlow<Boolean> = _apiError.asStateFlow()
    private val _groupIsNotAvailable: MutableStateFlow<Boolean> = MutableStateFlow(value = false)
    val groupIsNotAvailable: StateFlow<Boolean> = _groupIsNotAvailable.asStateFlow()
    private val _serverTimeout: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val serverTimeout: StateFlow<Boolean> = _serverTimeout.asStateFlow()

    fun internetIsNotAvailableNotify(){
        Log.i("WarningsNotificator", "internetIsNotAvailableNotify")
        _internetIsNotAvailable.value = true


    }

    fun internetIsNotAvailableNotifyOff(){
        _internetIsNotAvailable.value = false
    }



    fun apiErrorNotify(){
        _apiError.value = true

    }

    fun apiErrorNotifyOff(){
        _apiError.value = false
    }

    fun groupIsNotAvailableNotify(){
        _groupIsNotAvailable.value = true

    }

    fun groupIsNotAvailableNotifyOff(){
        _groupIsNotAvailable.value = false
    }

    fun serverTimeoutNotify(){
        _serverTimeout.value = true
        
    }

    fun serverTimeoutNotifyOff(){
        _serverTimeout.value = false
    }
}