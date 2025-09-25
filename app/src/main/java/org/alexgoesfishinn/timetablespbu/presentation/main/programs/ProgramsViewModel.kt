package org.alexgoesfishinn.timetablespbu.presentation.main.programs

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeProgramsUseCase
import org.alexgoesfishinn.timetablespbu.presentation.main.mappers.ProgramToUiMapper
import org.alexgoesfishinn.timetablespbu.presentation.main.model.ProgramItem
import javax.inject.Inject
@HiltViewModel
class ProgramsViewModel @Inject constructor(
    private val subscribeProgramsUseCase: SubscribeProgramsUseCase,
    private val programToUiMapper: ProgramToUiMapper,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _programs: MutableStateFlow<List<ProgramItem>> = MutableStateFlow(emptyList())
    val programs: StateFlow<List<ProgramItem>> = _programs.asStateFlow()

    init {
        Log.i(TAG, "init")
        val programCombinationId: Long? = savedStateHandle["program_combination_id"]
        Log.i(TAG, "programCombinationId = $programCombinationId")
        viewModelScope.launch {
            if(programCombinationId != null){
                _programs.value = subscribeProgramsUseCase.getPrograms(programCombinationId).map {
                    programToUiMapper.invoke(it)
                }
            }

        }
    }

    private companion object{
        const val TAG = "ProgramsViewModel"
    }
}