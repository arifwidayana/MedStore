package com.arifwidayana.medstore.data.network.datasource

import com.arifwidayana.medstore.common.base.BaseResponse
import com.arifwidayana.medstore.data.network.model.request.auth.login.LoginRequest
import com.arifwidayana.medstore.data.network.model.request.auth.register.RegisterRequest
import com.arifwidayana.medstore.data.network.model.response.auth.login.LoginResponse
import com.arifwidayana.medstore.data.network.model.response.auth.register.RegisterResponse
import com.arifwidayana.medstore.data.network.service.AuthService
import javax.inject.Inject

interface AuthDatasource {
    suspend fun loginUser(loginRequest: LoginRequest): BaseResponse<LoginResponse>
    suspend fun registerUser(registerRequest: RegisterRequest): BaseResponse<RegisterResponse>
}

class AuthDatasourceImpl @Inject constructor(
    private val authService: AuthService
): AuthDatasource {
    override suspend fun loginUser(loginRequest: LoginRequest): BaseResponse<LoginResponse> {
        return authService.loginUser(loginRequest)
    }

    override suspend fun registerUser(registerRequest: RegisterRequest): BaseResponse<RegisterResponse> {
        return authService.registerUser(registerRequest)
    }
}