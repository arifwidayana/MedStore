package com.arifwidayana.medstore.data.network.model.request.auth.register

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("profileName")
    val name: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("password")
    val password: String?
)
