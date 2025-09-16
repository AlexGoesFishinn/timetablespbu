package org.alexgoesfishinn.timetablespbu.presentation.main.mappers

import org.alexgoesfishinn.timetablespbu.domain.entities.DivisionN
import org.alexgoesfishinn.timetablespbu.presentation.main.model.DivisionItem
import javax.inject.Inject

class DivisionToUiMapper @Inject constructor(): (DivisionN) -> DivisionItem {
    override fun invoke(division: DivisionN): DivisionItem {
        return DivisionItem(
            name = division.name,
            alias = division.alias
        )
    }
}