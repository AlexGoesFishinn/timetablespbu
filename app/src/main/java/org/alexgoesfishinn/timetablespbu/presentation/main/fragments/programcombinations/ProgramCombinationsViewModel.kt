package org.alexgoesfishinn.timetablespbu.presentation.main.fragments.programcombinations

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.alexgoesfishinn.timetablespbu.data.utils.Notificator
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeProgramCombinationsUseCase
import org.alexgoesfishinn.timetablespbu.presentation.main.mappers.programcombination.ProgramCombinationToUiMapper
import org.alexgoesfishinn.timetablespbu.presentation.main.model.ProgramCombinationItem
import javax.inject.Inject
/**
 * @author a.bylev
 */
@HiltViewModel
class ProgramCombinationsViewModel @Inject constructor(
    private val subscribeProgramCombinationsUseCase: SubscribeProgramCombinationsUseCase,
    private val programCombinationToUiMapper: ProgramCombinationToUiMapper,
    savedStateHandle: SavedStateHandle,
    private val notificator: Notificator
): ViewModel(){
    private val _programCombinations: MutableStateFlow<List<ProgramCombinationItem>> = MutableStateFlow(
        emptyList()
    )
    val programCombinations: StateFlow<List<ProgramCombinationItem>> = _programCombinations.asStateFlow()


    init {
        Log.i(TAG, "init")
        val levelId: Long? = savedStateHandle["level_id"]
        Log.i(TAG, "levelId = $levelId")
        viewModelScope.launch {
            if(levelId != null){
                notificator.loadingInProcess()
                _programCombinations.value = subscribeProgramCombinationsUseCase.getProgramCombinations(levelId).map {
                    programCombinationToUiMapper.invoke(it)
                }
                notificator.loadingFinished()
                Log.i(TAG, "_programCombinations = ${_programCombinations.value}")
            }
        }
    }



    companion object{
        const val TAG = "ProgramCombinationsViewModel"
    }
}