package org.alexgoesfishinn.timetablespbu.data.storage.mappers.favourite

import org.alexgoesfishinn.timetablespbu.data.storage.entities.FavouriteDb
import org.alexgoesfishinn.timetablespbu.domain.entities.Favourite
import javax.inject.Inject
/**
 * @author a.bylev
 */
class FavouriteDbToDomainMapper @Inject constructor(): (FavouriteDb) -> Favourite {
    override fun invoke(p1: FavouriteDb): Favourite {
        return Favourite(
            displayName = p1.displayName,
            id = p1.id
        )
    }
}