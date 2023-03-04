package com.arifwidayana.medstore.data.network.model.request.product.add

data class AddProductParamRequest(
    val productName: String,
    val price: Int,
    val stock: Int,
    val supplierName: String,
    val supplierAddress: String,
    val supplierPhoneNumber: String
)