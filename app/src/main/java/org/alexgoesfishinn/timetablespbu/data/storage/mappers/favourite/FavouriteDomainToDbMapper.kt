package org.alexgoesfishinn.timetablespbu.data.storage.mappers.favourite

import org.alexgoesfishinn.timetablespbu.data.storage.entities.FavouriteDb
import org.alexgoesfishinn.timetablespbu.domain.entities.Favourite
import javax.inject.Inject

class FavouriteDomainToDbMapper @Inject constructor(): (Favourite) -> FavouriteDb {
    override fun invoke(p1: Favourite): FavouriteDb {
        return FavouriteDb(
            displayName = p1.displayName,
            id = p1.id
        )
    }
}