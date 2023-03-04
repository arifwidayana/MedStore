package com.arifwidayana.medstore.data.network.model.request.product

data class ProductParamRequest(
    val productName: String,
    val price: Int,
    val stock: Int,
    val supplierName: String
)