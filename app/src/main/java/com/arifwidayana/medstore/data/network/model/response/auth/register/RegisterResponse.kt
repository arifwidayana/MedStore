package com.arifwidayana.medstore.data.network.model.response.auth.register

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("profileName")
    val profileName: String?
)