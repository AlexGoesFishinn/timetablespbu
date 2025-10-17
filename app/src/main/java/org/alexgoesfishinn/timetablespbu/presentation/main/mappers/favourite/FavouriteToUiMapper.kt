package org.alexgoesfishinn.timetablespbu.presentation.main.mappers.favourite

import org.alexgoesfishinn.timetablespbu.domain.entities.Favourite
import org.alexgoesfishinn.timetablespbu.presentation.main.model.FavouriteItem
import javax.inject.Inject

class FavouriteToUiMapper @Inject constructor(): (Favourite) -> FavouriteItem {
    override fun invoke(p1: Favourite): FavouriteItem {
        return FavouriteItem(

            displayName = p1.displayName,
            id = p1.id
        )
    }
}