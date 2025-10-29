package org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.favourite

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
class FavouriteDialogViewModel @Inject constructor(
    private val subscribeFavouriteUseCase: SubscribeFavouriteUseCase,
    private val uiMapper: FavouriteToUiMapper,
    private val favouriteUpdateNotificator: FavouriteUpdateNotificator
) : ViewModel() {
    private val _favorite: MutableStateFlow<List<FavouriteItem>> = MutableStateFlow(emptyList())
    val favourite: StateFlow<List<FavouriteItem>> = _favorite.asStateFlow()

    init {
        getFavourite()
    }

    fun removeFromFavourite(id: Long) {
        viewModelScope.launch {
            subscribeFavouriteUseCase.delete(id)
            favouriteUpdateNotificator.favouriteUpdatedNotify()
            getFavourite()
        }
    }

    private fun getFavourite() {
        viewModelScope.launch {
            _favorite.value = subscribeFavouriteUseCase.getAll().map { uiMapper.invoke(it) }
        }
    }

}