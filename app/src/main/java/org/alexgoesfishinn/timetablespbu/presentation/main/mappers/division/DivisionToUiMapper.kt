package org.alexgoesfishinn.timetablespbu.presentation.main.mappers.division

import org.alexgoesfishinn.timetablespbu.domain.entities.Division
import org.alexgoesfishinn.timetablespbu.presentation.main.model.DivisionItem
import javax.inject.Inject
/**
 * @author a.bylev
 */
class DivisionToUiMapper @Inject constructor(): (Division) -> DivisionItem {
    override fun invoke(division: Division): DivisionItem {
        return DivisionItem(
            name = division.name,
            alias = division.alias
        )
    }
}