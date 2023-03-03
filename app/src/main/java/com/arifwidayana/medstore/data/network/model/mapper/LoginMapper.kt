package com.arifwidayana.medstore.data.network.model.mapper

import com.arifwidayana.medstore.common.utils.mapper.DataObjectMapper
import com.arifwidayana.medstore.data.network.model.request.auth.login.LoginParamRequest
import com.arifwidayana.medstore.data.network.model.request.auth.login.LoginRequest

object LoginRequestMapper : DataObjectMapper<LoginRequest, LoginParamRequest> {
    override fun toDataObject(viewParam: LoginParamRequest?): LoginRequest =
        LoginRequest(
            username = viewParam?.username,
            password = viewParam?.password
        )
}