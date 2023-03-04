package com.arifwidayana.medstore.presentation.ui.supplier.add

import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.network.model.request.supplier.SupplierParamRequest
import kotlinx.coroutines.flow.StateFlow

interface AddSupplierContract {
    val addSupplierResult: StateFlow<Resource<Unit>>
    fun addSupplier(supplierParamRequest: SupplierParamRequest)
}