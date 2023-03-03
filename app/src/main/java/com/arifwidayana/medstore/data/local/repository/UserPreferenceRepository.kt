package com.arifwidayana.medstore.data.local.repository

import com.arifwidayana.medstore.common.base.BaseRepository
import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.local.datasource.UserPreferenceDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface UserPreferenceRepository {
    suspend fun setToken(name: String) : Flow<Resource<Unit>>
    suspend fun getToken(): Flow<Resource<String>>
    suspend fun logoutUser(): Flow<Resource<Unit>>
}

class UserPreferenceRepositoryImpl @Inject constructor(
    private val userPreferenceDatasource: UserPreferenceDatasource
) : UserPreferenceRepository, BaseRepository() {
    override suspend fun setToken(name: String): Flow<Resource<Unit>> = flow {
        emit(proceed { userPreferenceDatasource.setToken(name) })
    }

    override suspend fun getToken(): Flow<Resource<String>> = flow {
        emit(proceed { userPreferenceDatasource.getToken().first() })
    }

    override suspend fun logoutUser(): Flow<Resource<Unit>> = flow {
        emit(proceed { userPreferenceDatasource.logoutUser() })
    }
}