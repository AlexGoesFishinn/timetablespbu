package org.alexgoesfishinn.timetablespbu.data.repo

import org.alexgoesfishinn.timetablespbu.data.storage.dao.FavouriteDao
import org.alexgoesfishinn.timetablespbu.data.storage.mappers.favourite.FavouriteDbToDomainMapper
import org.alexgoesfishinn.timetablespbu.data.storage.mappers.favourite.FavouriteDomainToDbMapper
import org.alexgoesfishinn.timetablespbu.domain.entities.Favourite
import javax.inject.Inject

interface FavouriteRepository {
    suspend fun getAll(): List<Favourite>

    suspend fun delete(oid: Long)

    suspend fun add(favourite: Favourite)
}

class SubscribeFavouriteRepositoryImpl @Inject constructor(
    private val favouriteDbToDomainMapper: FavouriteDbToDomainMapper,
    private val favouriteDomainToDbMapper: FavouriteDomainToDbMapper,
    private val favouriteDao: FavouriteDao
): FavouriteRepository{
    override suspend fun getAll(): List<Favourite> {
        return favouriteDao.selectAll().map { favouriteDbToDomainMapper.invoke(it) }
    }

    override suspend fun delete(oid: Long) {
        favouriteDao.delete(oid)
    }

    override suspend fun add(favourite: Favourite) {
        favouriteDao.insert(favouriteDomainToDbMapper.invoke(favourite))
    }
}