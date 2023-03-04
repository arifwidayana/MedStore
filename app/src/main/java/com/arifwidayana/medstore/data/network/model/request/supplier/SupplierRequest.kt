package com.arifwidayana.medstore.data.network.model.request.supplier

import com.google.gson.annotations.SerializedName

data class SupplierRequest(
    @SerializedName("namaSupplier")
    val supplierName: String?,
    @SerializedName("alamat")
    val supplierAddress: String?,
    @SerializedName("noTelp")
    val supplierPhoneNumber: String?
)
