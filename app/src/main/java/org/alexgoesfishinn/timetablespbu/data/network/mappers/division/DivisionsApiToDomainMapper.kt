package org.alexgoesfishinn.timetablespbu.data.network.mappers.division

import org.alexgoesfishinn.timetablespbu.data.network.entities.DivisionApi
import org.alexgoesfishinn.timetablespbu.domain.entities.DivisionN
import javax.inject.Inject

class DivisionsApiToDomainMapper @Inject constructor(): (DivisionApi) -> DivisionN {
    override fun invoke(division: DivisionApi): DivisionN {
        return DivisionN(
            name = division.name,
            alias = division.alias
        )
    }
}