package com.arifwidayana.medstore.data.network.model.request.product

import com.google.gson.annotations.SerializedName

data class ProductRequest(
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
        val supplierName: String?
    )
}