package com.arifwidayana.medstore.data.network.model.response.supplier

data class SupplierParamResponse(
    val supplierId: Int,
    val supplierName: String,
    val supplierAddress: String,
    val supplierPhoneNumber: String
)
