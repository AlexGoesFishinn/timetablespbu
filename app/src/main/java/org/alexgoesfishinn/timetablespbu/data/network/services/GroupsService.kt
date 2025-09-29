package org.alexgoesfishinn.timetablespbu.data.network.services


import org.alexgoesfishinn.timetablespbu.data.network.entities.ProgramGroupsApi
import retrofit2.http.GET
import retrofit2.http.Path
/**
 * @author a.bylev
 */
interface GroupsService {

      @GET("programs/{id}/groups")
      suspend fun getGroups(@Path("id") id: Long): ProgramGroupsApi
}