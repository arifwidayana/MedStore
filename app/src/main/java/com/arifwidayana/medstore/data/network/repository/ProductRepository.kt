package com.arifwidayana.medstore.data.network.repository

import androidx.paging.PagingData
import com.arifwidayana.medstore.common.base.BaseRepository
import com.arifwidayana.medstore.common.base.BaseResponse
import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.network.datasource.ProductDatasource
import com.arifwidayana.medstore.data.network.model.request.product.ProductRequest
import com.arifwidayana.medstore.data.network.model.response.product.ProductParamResponse
import com.arifwidayana.medstore.data.network.model.response.product.ProductResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

typealias ProductDataResource = Resource<BaseResponse<ProductResponse>>

interface ProductRepository {
    suspend fun postAddProduct(productRequest: ProductRequest): Flow<ProductDataResource>
    suspend fun getAllProduct(): Flow<PagingData<ProductParamResponse>>
    suspend fun getDetailProduct(idProduct: Int): Flow<ProductDataResource>
    suspend fun updateProduct(idProduct: Int, productRequest: ProductRequest): Flow<ProductDataResource>
    suspend fun deleteProduct(idProduct: Int): Flow<ProductDataResource>
}

class ProductRepositoryImpl @Inject constructor(
    private val productDatasource: ProductDatasource
): ProductRepository, BaseRepository() {
    override suspend fun postAddProduct(productRequest: ProductRequest): Flow<ProductDataResource> = flow {
        emit(proceed { productDatasource.postAddProduct(productRequest) })
    }

    override suspend fun getAllProduct(): Flow<PagingData<ProductParamResponse>> {
        return productDatasource.getAllProduct()
    }

    override suspend fun getDetailProduct(idProduct: Int): Flow<ProductDataResource> = flow {
        emit(proceed { productDatasource.getDetailProduct(idProduct) })
    }

    override suspend fun updateProduct(idProduct: Int, productRequest: ProductRequest): Flow<ProductDataResource> = flow {
        emit(proceed { productDatasource.updateProduct(idProduct, productRequest) })
    }

    override suspend fun deleteProduct(idProduct: Int): Flow<ProductDataResource> = flow {
        emit(proceed { productDatasource.deleteProduct(idProduct) })
    }
}