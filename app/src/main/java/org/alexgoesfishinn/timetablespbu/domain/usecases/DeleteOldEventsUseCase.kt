package org.alexgoesfishinn.timetablespbu.domain.usecases


import org.alexgoesfishinn.timetablespbu.data.storage.utils.DbCleaner
import javax.inject.Inject
/**
 * @author a.bylev
 */
interface DeleteOldEventsUseCase {
    suspend fun deleteOldEvents()
}

class DeleteOldEventsUseCaseImpl @Inject constructor(
    private val dbCleaner: DbCleaner
) : DeleteOldEventsUseCase{
    override suspend fun deleteOldEvents() {
        dbCleaner.deleteOldEvents()
    }
}