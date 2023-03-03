package com.arifwidayana.medstore.data.network.model.request.auth.login

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("username")
    val username: String?,
    @SerializedName("password")
    val password: String?
)
