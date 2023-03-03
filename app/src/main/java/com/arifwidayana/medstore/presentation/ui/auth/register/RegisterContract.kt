package com.arifwidayana.medstore.presentation.ui.auth.register

import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.network.model.request.auth.register.RegisterParamRequest
import kotlinx.coroutines.flow.StateFlow

typealias RegisterDataResult = Resource<Boolean>

interface RegisterContract {
    val registerUserResult: StateFlow<RegisterDataResult>
    fun registerUser(registerParamRequest: RegisterParamRequest)
}