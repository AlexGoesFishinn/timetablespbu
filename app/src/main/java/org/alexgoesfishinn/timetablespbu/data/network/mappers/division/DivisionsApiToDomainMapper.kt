package org.alexgoesfishinn.timetablespbu.data.network.mappers.division

import org.alexgoesfishinn.timetablespbu.data.network.entities.DivisionApi
import org.alexgoesfishinn.timetablespbu.domain.entities.Division
import javax.inject.Inject

class DivisionsApiToDomainMapper @Inject constructor(): (DivisionApi) -> Division {
    override fun invoke(division: DivisionApi): Division {
        return Division(
            name = division.name,
            alias = division.alias
        )
    }
}