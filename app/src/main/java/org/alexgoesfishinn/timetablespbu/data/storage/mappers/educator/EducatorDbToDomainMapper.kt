package org.alexgoesfishinn.timetablespbu.data.storage.mappers.educator

import org.alexgoesfishinn.timetablespbu.data.storage.entities.EducatorDb
import org.alexgoesfishinn.timetablespbu.domain.entities.Educator
import javax.inject.Inject

class EducatorDbToDomainMapper @Inject constructor(): (EducatorDb) -> Educator {
    override fun invoke(p1: EducatorDb): Educator {
        return Educator(
            name = p1.name
        )
    }
}