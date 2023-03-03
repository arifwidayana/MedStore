package com.arifwidayana.medstore.common.base

import com.google.gson.annotations.SerializedName

data class BaseResponse<D>(
    @SerializedName("data")
    val data: D,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("total_page")
    val totalPage: Int?,
    @SerializedName("total_record")
    val totalRecord: Int?
)