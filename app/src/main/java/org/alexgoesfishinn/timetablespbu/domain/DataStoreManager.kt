package org.alexgoesfishinn.timetablespbu.domain

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


/**
 * @author a.bylev
 */
private val Context.dataStore by preferencesDataStore("user-favourite-group")
class DataStoreManager(private val context: Context) {

    suspend fun save(groupID: String, groupName: String){
        context.dataStore.edit { settings ->
            settings[FIELD_GROUP_ID] = groupID
            settings[FIELD_GROUP_NAME] = groupName

        }
    }

    suspend fun getGroupId():String = context.dataStore.data.map {
        pref -> pref[FIELD_GROUP_ID] ?: ""
    }.first()

    suspend fun getGroupName(): String = context.dataStore.data.map {
        pref -> pref[FIELD_GROUP_NAME] ?: ""
    }.first()

    private companion object{
        private val FIELD_GROUP_NAME = stringPreferencesKey("group_name")
        private val FIELD_GROUP_ID = stringPreferencesKey("group_id")
    }

}