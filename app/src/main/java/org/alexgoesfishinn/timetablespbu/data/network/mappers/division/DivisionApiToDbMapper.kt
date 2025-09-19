package org.alexgoesfishinn.timetablespbu.data.network.mappers.division

import org.alexgoesfishinn.timetablespbu.data.network.entities.DivisionApi
import org.alexgoesfishinn.timetablespbu.data.storage.entities.DivisionDb
import javax.inject.Inject

class DivisionApiToDbMapper @Inject constructor(): (DivisionApi) -> DivisionDb {
    override fun invoke(divisionApi: DivisionApi): DivisionDb {
        return DivisionDb(
            oid = divisionApi.oid,
            name = divisionApi.name,
            alias = divisionApi.alias
        )
    }
}