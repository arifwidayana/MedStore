package com.arifwidayana.medstore.data.network.model.request.auth.register

data class RegisterParamRequest(
    val name: String,
    val username: String,
    val password: String
)
