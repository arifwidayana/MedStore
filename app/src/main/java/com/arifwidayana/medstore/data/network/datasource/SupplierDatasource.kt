package com.arifwidayana.medstore.data.network.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.arifwidayana.medstore.common.base.BaseResponse
import com.arifwidayana.medstore.common.utils.Constant
import com.arifwidayana.medstore.data.network.model.request.supplier.SupplierRequest
import com.arifwidayana.medstore.data.network.model.response.supplier.SupplierParamResponse
import com.arifwidayana.medstore.data.network.model.response.supplier.SupplierResponse
import com.arifwidayana.medstore.data.network.service.SupplierService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SupplierDatasource {
    suspend fun postAddSupplier(supplierRequest: SupplierRequest): BaseResponse<SupplierResponse>
    suspend fun getAllSupplier(): Flow<PagingData<SupplierParamResponse>>
    suspend fun getDetailSupplier(idProduct: Int): BaseResponse<SupplierResponse>
    suspend fun updateSupplier(idProduct: Int, supplierRequest: SupplierRequest): BaseResponse<SupplierResponse>
    suspend fun deleteSupplier(idProduct: Int): BaseResponse<SupplierResponse>
}

class SupplierDatasourceImpl @Inject constructor(
    private val supplierService: SupplierService
): SupplierDatasource {
    override suspend fun postAddSupplier(supplierRequest: SupplierRequest): BaseResponse<SupplierResponse> {
        return supplierService.postAddSupplier(supplierRequest)
    }

    override suspend fun getAllSupplier(): Flow<PagingData<SupplierParamResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constant.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PagingSupplierDatasource(supplierService)
            }
        ).flow
    }

    override suspend fun getDetailSupplier(idProduct: Int): BaseResponse<SupplierResponse> {
        return supplierService.getDetailSupplier(idProduct)
    }

    override suspend fun updateSupplier(
        idProduct: Int,
        supplierRequest: SupplierRequest
    ): BaseResponse<SupplierResponse> {
        return supplierService.updateSupplier(idProduct, supplierRequest)
    }

    override suspend fun deleteSupplier(idProduct: Int): BaseResponse<SupplierResponse> {
        return supplierService.deleteSupplier(idProduct)
    }
}