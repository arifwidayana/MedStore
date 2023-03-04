package com.arifwidayana.medstore.data.network.model.request.product.add

import com.google.gson.annotations.SerializedName

data class AddProductRequest(
    @SerializedName("namaBarang")
    val productName: String?,
    @SerializedName("harga")
    val price: Int?,
    @SerializedName("stok")
    val stock: Int?,
    @SerializedName("supplier")
    val supplier: Supplier?
) {
    data class Supplier(
        @SerializedName("namaSupplier")
        val supplierName: String?,
        @SerializedName("alamat")
        val supplierAddress: String?,
        @SerializedName("noTelp")
        val supplierPhoneNumber: String?
    )
}