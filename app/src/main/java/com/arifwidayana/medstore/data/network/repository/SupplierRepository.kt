package com.arifwidayana.medstore.data.network.repository

import androidx.paging.PagingData
import com.arifwidayana.medstore.common.base.BaseRepository
import com.arifwidayana.medstore.common.base.BaseResponse
import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.network.datasource.SupplierDatasource
import com.arifwidayana.medstore.data.network.model.request.supplier.SupplierRequest
import com.arifwidayana.medstore.data.network.model.response.supplier.SupplierParamResponse
import com.arifwidayana.medstore.data.network.model.response.supplier.SupplierResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

typealias SupplierDataResource = Resource<BaseResponse<SupplierResponse>>

interface SupplierRepository {
    suspend fun postAddSupplier(supplierRequest: SupplierRequest): Flow<SupplierDataResource>
    suspend fun getAllProduct(): Flow<PagingData<SupplierParamResponse>>
    suspend fun getDetailSupplier(idProduct: Int): Flow<SupplierDataResource>
    suspend fun updateSupplier(
        idProduct: Int,
        supplierRequest: SupplierRequest
    ): Flow<SupplierDataResource>
    suspend fun deleteSupplier(idProduct: Int): Flow<SupplierDataResource>
}

class SupplierRepositoryImpl @Inject constructor(
    private val supplierDatasource: SupplierDatasource
) : SupplierRepository, BaseRepository() {
    override suspend fun postAddSupplier(supplierRequest: SupplierRequest): Flow<SupplierDataResource> = flow {
        emit(proceed { supplierDatasource.postAddSupplier(supplierRequest) })
    }

    override suspend fun getAllProduct(): Flow<PagingData<SupplierParamResponse>> {
        return supplierDatasource.getAllSupplier()
    }

    override suspend fun getDetailSupplier(idProduct: Int): Flow<SupplierDataResource> = flow {
        emit(proceed { supplierDatasource.getDetailSupplier(idProduct) })
    }

    override suspend fun updateSupplier(
        idProduct: Int,
        supplierRequest: SupplierRequest
    ): Flow<SupplierDataResource> = flow {
        emit(proceed { supplierDatasource.updateSupplier(idProduct, supplierRequest) })
    }

    override suspend fun deleteSupplier(idProduct: Int): Flow<SupplierDataResource> = flow {
        emit(proceed { supplierDatasource.deleteSupplier(idProduct) })
    }
}