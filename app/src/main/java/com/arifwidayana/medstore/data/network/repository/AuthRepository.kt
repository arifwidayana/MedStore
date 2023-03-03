package com.arifwidayana.medstore.data.network.repository

import com.arifwidayana.medstore.common.base.BaseRepository
import com.arifwidayana.medstore.common.base.BaseResponse
import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.network.datasource.AuthDatasource
import com.arifwidayana.medstore.data.network.model.request.auth.login.LoginRequest
import com.arifwidayana.medstore.data.network.model.request.auth.register.RegisterRequest
import com.arifwidayana.medstore.data.network.model.response.auth.login.LoginResponse
import com.arifwidayana.medstore.data.network.model.response.auth.register.RegisterResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

typealias LoginDataResource = Resource<BaseResponse<LoginResponse>>
typealias RegisterDataResource = Resource<BaseResponse<RegisterResponse>>

interface AuthRepository {
    suspend fun loginUser(loginRequest: LoginRequest): Flow<LoginDataResource>
    suspend fun registerUser(registerRequest: RegisterRequest): Flow<RegisterDataResource>
}

class AuthRepositoryImpl @Inject constructor(
    private val authDatasource: AuthDatasource
): AuthRepository, BaseRepository() {
    override suspend fun loginUser(loginRequest: LoginRequest): Flow<LoginDataResource> = flow {
        emit(proceed { authDatasource.loginUser(loginRequest) })
    }

    override suspend fun registerUser(registerRequest: RegisterRequest): Flow<RegisterDataResource> = flow {
        emit(proceed { authDatasource.registerUser(registerRequest) })
    }
}