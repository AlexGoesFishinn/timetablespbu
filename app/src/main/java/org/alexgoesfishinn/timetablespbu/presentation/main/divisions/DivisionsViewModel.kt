package org.alexgoesfishinn.timetablespbu.presentation.main.divisions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeDivisionsUseCase
import org.alexgoesfishinn.timetablespbu.presentation.main.mappers.DivisionToUiMapper
import org.alexgoesfishinn.timetablespbu.presentation.main.model.DivisionItem
import javax.inject.Inject

@HiltViewModel
class DivisionsViewModel @Inject constructor(
    private val subscribeDivisionsUseCase: SubscribeDivisionsUseCase,
    private val uiMapper: DivisionToUiMapper
): ViewModel() {

    private val _divisions: MutableStateFlow<List<DivisionItem>> = MutableStateFlow(emptyList())
    val divisions: StateFlow<List<DivisionItem>> = _divisions.asStateFlow()

    init {
        viewModelScope.launch {
            _divisions.value =
                subscribeDivisionsUseCase
                    .getDivisions()
                    .map { uiMapper.invoke(it) }
        }
    }
}