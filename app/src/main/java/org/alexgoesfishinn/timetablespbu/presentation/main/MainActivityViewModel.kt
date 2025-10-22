package org.alexgoesfishinn.timetablespbu.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.alexgoesfishinn.timetablespbu.data.network.utils.InternetChecker
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val internetChecker: InternetChecker
): ViewModel() {
    private val _isInternetAvailable: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isInternetAvailable: StateFlow<Boolean> = _isInternetAvailable.asStateFlow()
    private val _apiError: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val apiError: StateFlow<Boolean> = _apiError.asStateFlow()

    init {
        subscribeInternet()
        subscribeApiError()
    }

    private fun subscribeInternet(){
        viewModelScope.launch {
            internetChecker.isInternetAvailableFlag.collect{
                _isInternetAvailable.value = it
            }
        }
    }

    private fun subscribeApiError(){
        viewModelScope.launch {
            internetChecker.apiError.collect {
                _apiError.value = it
            }
        }
    }
}