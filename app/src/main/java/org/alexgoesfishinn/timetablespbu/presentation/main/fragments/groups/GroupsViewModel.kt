package org.alexgoesfishinn.timetablespbu.presentation.main.fragments.groups

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.alexgoesfishinn.timetablespbu.data.utils.Notificator
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeGroupsUseCase
import org.alexgoesfishinn.timetablespbu.presentation.main.mappers.group.GroupToUiMapper
import org.alexgoesfishinn.timetablespbu.presentation.main.model.GroupItem
import javax.inject.Inject
/**
 * @author a.bylev
 */
@HiltViewModel
class GroupsViewModel @Inject constructor(
    private val subscribeGroupsUseCase: SubscribeGroupsUseCase,
    private val groupToUiMapper: GroupToUiMapper,
    savedStateHandle: SavedStateHandle,
    private val notificator: Notificator
): ViewModel() {

    private val _groups: MutableStateFlow<List<GroupItem>> = MutableStateFlow(emptyList())
    val groups: StateFlow<List<GroupItem>> = _groups.asStateFlow()

    init {
        val id: Long? = savedStateHandle["program_id"]
        viewModelScope.launch {
            if(id != null){
                notificator.loadingInProcess()
                _groups.value = subscribeGroupsUseCase.getGroups(id).map { groupToUiMapper.invoke(it) }
                notificator.loadingFinished()
            }

        }
    }


}