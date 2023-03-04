package com.arifwidayana.medstore.presentation.ui.supplier.detail

import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.network.model.request.supplier.SupplierParamRequest
import com.arifwidayana.medstore.data.network.model.response.supplier.SupplierParamResponse
import kotlinx.coroutines.flow.StateFlow

interface DetailSupplierContract {
    val getSupplierResult : StateFlow<Resource<SupplierParamResponse>>
    val updateSupplierResult : StateFlow<Resource<Unit>>
    val deleteSupplierResult : StateFlow<Resource<Unit>>
    fun getSupplier(idProduct: Int)
    fun updateSupplier(idProduct: Int, supplierParamRequest: SupplierParamRequest)
    fun deleteSupplier(idProduct: Int)
}