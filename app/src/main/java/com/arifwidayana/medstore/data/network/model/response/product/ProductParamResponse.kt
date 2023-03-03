package com.arifwidayana.medstore.data.network.model.response.product

data class ProductParamResponse(
    val id: Int,
    val productName: String,
    val price: Int,
    val stock: Int,
    val supplier: Supplier
) {
    data class Supplier(
        val id: Int,
        val supplierName: String,
        val phoneNumber: String,
        val address: String
    )
}