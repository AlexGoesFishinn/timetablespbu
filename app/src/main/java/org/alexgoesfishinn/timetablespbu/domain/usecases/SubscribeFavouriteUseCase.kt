package org.alexgoesfishinn.timetablespbu.domain.usecases

import org.alexgoesfishinn.timetablespbu.data.repo.FavouriteRepository
import org.alexgoesfishinn.timetablespbu.domain.entities.Favourite
import javax.inject.Inject

/**
 * @author a.bylev
 */
interface SubscribeFavouriteUseCase {
    suspend fun getAll(): List<Favourite>

    suspend fun delete(id: Long)

    suspend fun insert(favourite: Favourite)
}

class SubscribeFavouriteUseCaseImpl @Inject constructor(
    private val favouriteRepository: FavouriteRepository
) : SubscribeFavouriteUseCase {
    override suspend fun getAll(): List<Favourite> {
        return favouriteRepository.getAll().sortedBy { it.displayName }
    }

    override suspend fun delete(id: Long) {
        favouriteRepository.delete(id)
    }

    override suspend fun insert(favourite: Favourite) {
        favouriteRepository.add(favourite)
    }
}