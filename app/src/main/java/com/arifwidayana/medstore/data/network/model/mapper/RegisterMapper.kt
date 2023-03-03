package com.arifwidayana.medstore.data.network.model.mapper

import com.arifwidayana.medstore.common.utils.mapper.DataObjectMapper
import com.arifwidayana.medstore.data.network.model.request.auth.register.RegisterParamRequest
import com.arifwidayana.medstore.data.network.model.request.auth.register.RegisterRequest

object RegisterRequestMapper : DataObjectMapper<RegisterRequest, RegisterParamRequest> {
    override fun toDataObject(viewParam: RegisterParamRequest?): RegisterRequest =
        RegisterRequest(
            name = viewParam?.name,
            username = viewParam?.username,
            password = viewParam?.password
        )
}