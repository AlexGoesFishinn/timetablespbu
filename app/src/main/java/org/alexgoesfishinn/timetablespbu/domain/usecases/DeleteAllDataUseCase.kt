package org.alexgoesfishinn.timetablespbu.domain.usecases

import org.alexgoesfishinn.timetablespbu.data.storage.utils.DbCleaner
import javax.inject.Inject
/**
 * @author a.bylev
 */
interface DeleteAllDataUseCase {
    suspend fun deleteAllData()
}

class DeleteAllDataUseCaseImpl @Inject constructor(
    private val dbCleaner: DbCleaner
): DeleteAllDataUseCase{
    override suspend fun deleteAllData() {
        dbCleaner.deleteAllData()
    }
}