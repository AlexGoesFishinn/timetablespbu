package org.alexgoesfishinn.timetablespbu.domain.usecases

import org.alexgoesfishinn.timetablespbu.data.repo.FavouriteRepository
import org.alexgoesfishinn.timetablespbu.domain.entities.Favourite
import javax.inject.Inject

interface SubscribeFavouriteUseCase {
    suspend fun getAll(): List<Favourite>

    suspend fun delete(oid: Long)

    suspend fun insert(favourite: Favourite)
}

class SubscribeFavouriteUseCaseImpl @Inject constructor(
    private val favouriteRepository: FavouriteRepository
):SubscribeFavouriteUseCase{
    override suspend fun getAll(): List<Favourite> {
        return favouriteRepository.getAll()
    }

    override suspend fun delete(oid: Long) {
        favouriteRepository.delete(oid)
    }

    override suspend fun insert(favourite: Favourite) {
        favouriteRepository.add(favourite)
    }
}