package org.alexgoesfishinn.timetablespbu.data.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
/**
 * @author a.bylev
 */
class Notificator {
    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun loadingInProcess(){
        _isLoading.value = true
    }

    fun loadingFinished(){
        _isLoading.value = false
    }
}