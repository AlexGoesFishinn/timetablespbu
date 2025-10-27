package org.alexgoesfishinn.timetablespbu.data.network.mappers.educator

import org.alexgoesfishinn.timetablespbu.data.network.entities.EducatorApi
import org.alexgoesfishinn.timetablespbu.data.storage.entities.EducatorDb
import javax.inject.Inject
/**
 * @author a.bylev
 */
class EducatorApiToDbMapper @Inject constructor(): (EducatorApi) -> EducatorDb{
    override fun invoke(p1: EducatorApi): EducatorDb {
        return EducatorDb(
            item1 = p1.item1,
            name = p1.name
        )
    }
}