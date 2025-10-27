package org.alexgoesfishinn.timetablespbu.presentation.main.fragments.levels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeLevelsUseCase
import org.alexgoesfishinn.timetablespbu.presentation.main.mappers.level.LevelToUiMapper
import org.alexgoesfishinn.timetablespbu.presentation.main.model.LevelItem
import javax.inject.Inject
/**
 * @author a.bylev
 */
@HiltViewModel
class LevelsViewModel @Inject constructor(
    private val subscribeLevelsUseCase: SubscribeLevelsUseCase,
    private val uiMapper: LevelToUiMapper,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _levels: MutableStateFlow<List<LevelItem>> = MutableStateFlow(emptyList())
    val levels: StateFlow<List<LevelItem>> = _levels.asStateFlow()


    init {
        Log.i(TAG, "init")
        val alias: String? = savedStateHandle["alias"]
        Log.i(TAG, "alias = $alias")
        viewModelScope.launch {
            if(alias != null){
                _levels.value = subscribeLevelsUseCase
                    .getLevels(alias)
                    .map { uiMapper.invoke(it) }
            }

        }
    }

    companion object{
        private const val TAG = "LevelsViewModel"
    }
}