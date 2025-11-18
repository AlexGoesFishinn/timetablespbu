package org.alexgoesfishinn.timetablespbu.presentation.main.fragments.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.alexgoesfishinn.timetablespbu.data.storage.utils.FavouriteUpdateNotificator
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeFavouriteUseCase
import org.alexgoesfishinn.timetablespbu.presentation.main.mappers.favourite.FavouriteToUiMapper
import org.alexgoesfishinn.timetablespbu.presentation.main.model.FavouriteItem
import javax.inject.Inject
/**
 * @author a.bylev
 */
@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val subscribeFavouriteUseCase: SubscribeFavouriteUseCase,
    private val uiMapper: FavouriteToUiMapper,
    private val notificator: FavouriteUpdateNotificator
): ViewModel() {
    private val _favourite: MutableStateFlow<List<FavouriteItem>> = MutableStateFlow(emptyList())
    val favourite: StateFlow<List<FavouriteItem>> = _favourite.asStateFlow()

    init {
        getFavourite()
        subscribeNotificator()
    }

    private fun getFavourite(){
        viewModelScope.launch {
            _favourite.value = subscribeFavouriteUseCase.getAll().map { uiMapper.invoke(it) }
            notificator.favouriteUpdatedNotified()
        }
    }
    private fun subscribeNotificator(){
        viewModelScope.launch {
            notificator.favouriteUpdated.collect {
                if(it){
                    getFavourite()
                }
            }
        }
    }

}