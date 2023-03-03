package com.arifwidayana.medstore.data.local.datasource

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.arifwidayana.medstore.common.utils.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface UserPreferenceDatasource {
    suspend fun setToken(name: String)
    suspend fun getToken(): Flow<String>
    suspend fun logoutUser()
}

class UserPreferenceDatasourceImpl @Inject constructor(
    private val context: Context
): UserPreferenceDatasource {
    override suspend fun setToken(name: String) {
        context.dataStore.edit {
            it[tokenPref] = name
        }
    }

    override suspend fun getToken(): Flow<String> {
        return context.dataStore.data.map {
            it[tokenPref].orEmpty()
        }
    }

    override suspend fun logoutUser() {
        context.dataStore.edit {
            it.remove(tokenPref)
        }
    }

    companion object {
        private val Context.dataStore by preferencesDataStore(Constant.DATASTORE_PREF)
        private val tokenPref = stringPreferencesKey(Constant.TOKEN_PREF)
    }
}