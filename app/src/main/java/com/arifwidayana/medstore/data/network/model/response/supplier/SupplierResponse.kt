package com.arifwidayana.medstore.data.network.model.response.supplier

import com.google.gson.annotations.SerializedName

data class SupplierResponse(
    @SerializedName("id")
    val supplierId: Int?,
    @SerializedName("namaSupplier")
    val supplierName: String?,
    @SerializedName("alamat")
    val supplierAddress: String?,
    @SerializedName("noTelp")
    val supplierPhoneNumber: String?
)
