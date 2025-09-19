package org.alexgoesfishinn.timetablespbu.data.storage.mappers.division

import org.alexgoesfishinn.timetablespbu.data.storage.entities.DivisionDb
import org.alexgoesfishinn.timetablespbu.domain.entities.Division
import javax.inject.Inject

class DivisionDbToDomainMapper @Inject constructor(): (DivisionDb) -> Division {
    override fun invoke(divisionDB: DivisionDb): Division {
        return Division(
            name = divisionDB.name,
            alias = divisionDB.alias
        )
    }
}