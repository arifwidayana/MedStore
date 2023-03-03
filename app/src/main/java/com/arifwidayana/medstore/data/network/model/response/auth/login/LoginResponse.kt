package com.arifwidayana.medstore.data.network.model.response.auth.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("profileName")
    val profileName: String?,
    @SerializedName("token")
    val token: String?
)