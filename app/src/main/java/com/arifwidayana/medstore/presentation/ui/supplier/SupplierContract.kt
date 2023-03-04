package com.arifwidayana.medstore.presentation.ui.supplier

import androidx.paging.PagingData
import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.network.model.response.supplier.SupplierParamResponse
import kotlinx.coroutines.flow.StateFlow

typealias SupplierDataResult = PagingData<SupplierParamResponse>

interface SupplierContract {
    val getSupplierResult: StateFlow<Resource<SupplierDataResult>>
    fun getSupplier()
}