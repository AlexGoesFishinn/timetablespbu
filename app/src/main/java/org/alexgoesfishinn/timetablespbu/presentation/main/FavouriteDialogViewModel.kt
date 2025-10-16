package org.alexgoesfishinn.timetablespbu.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeFavouriteUseCase
import org.alexgoesfishinn.timetablespbu.presentation.main.mappers.favourite.FavouriteToUiMapper
import org.alexgoesfishinn.timetablespbu.presentation.main.model.FavouriteItem
import javax.inject.Inject
@HiltViewModel
class FavouriteDialogViewModel @Inject constructor(
    private val subscribeFavouriteUseCase: SubscribeFavouriteUseCase,
    private val uiMapper: FavouriteToUiMapper
): ViewModel() {
    private val _favorite: MutableStateFlow<List<FavouriteItem>> = MutableStateFlow(emptyList())
    val favourite: StateFlow<List<FavouriteItem>> = _favorite.asStateFlow()

    init {
        viewModelScope.launch {
            _favorite.value = subscribeFavouriteUseCase.getAll().map { uiMapper.invoke(it) }
        }
    }
}