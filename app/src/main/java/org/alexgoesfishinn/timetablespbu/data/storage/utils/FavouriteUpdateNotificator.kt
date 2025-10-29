package org.alexgoesfishinn.timetablespbu.data.storage.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
/**
 * @author a.bylev
 */
class FavouriteUpdateNotificator {
    private val _favouriteUpdated: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val favouriteUpdated: StateFlow<Boolean> = _favouriteUpdated.asStateFlow()

    fun favouriteUpdatedNotify(){
        _favouriteUpdated.value = true
    }

    fun favouriteUpdatedNotified(){
        _favouriteUpdated.value = false
    }
}