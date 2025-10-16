package org.alexgoesfishinn.timetablespbu.presentation.main.mappers.favourite

import org.alexgoesfishinn.timetablespbu.domain.entities.Favourite
import org.alexgoesfishinn.timetablespbu.presentation.main.model.FavouriteItem
import javax.inject.Inject

class FavouriteUiToDomainMapper @Inject constructor(): (FavouriteItem) -> Favourite {
    override fun invoke(p1: FavouriteItem): Favourite {
        return Favourite(
            displayName = p1.displayName,
            id = p1.id
        )
    }
}