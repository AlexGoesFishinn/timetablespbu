package org.alexgoesfishinn.timetablespbu.presentation.main.mappers.educator

import org.alexgoesfishinn.timetablespbu.domain.entities.Educator
import org.alexgoesfishinn.timetablespbu.presentation.main.model.EducatorItem
import javax.inject.Inject

class EducatorToUiMapper @Inject constructor(): (Educator) -> EducatorItem {
    override fun invoke(p1: Educator): EducatorItem {
        return EducatorItem(
            name = p1.name
        )
    }
}