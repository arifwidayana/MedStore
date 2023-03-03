package com.arifwidayana.medstore.presentation.ui.auth.login

import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.network.model.request.auth.login.LoginParamRequest
import com.arifwidayana.medstore.data.network.model.response.auth.login.LoginResponse
import kotlinx.coroutines.flow.StateFlow

typealias LoginDataResult = LoginResponse

interface LoginContract {
    val loginUserResult: StateFlow<Resource<LoginDataResult>>
    fun loginUser(loginParamRequest: LoginParamRequest)
}