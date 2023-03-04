package com.arifwidayana.medstore.data.network.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.arifwidayana.medstore.common.base.BaseResponse
import com.arifwidayana.medstore.common.utils.Constant.NETWORK_PAGE_SIZE
import com.arifwidayana.medstore.data.network.model.request.product.ProductRequest
import com.arifwidayana.medstore.data.network.model.request.product.add.AddProductRequest
import com.arifwidayana.medstore.data.network.model.response.product.ProductParamResponse
import com.arifwidayana.medstore.data.network.model.response.product.ProductResponse
import com.arifwidayana.medstore.data.network.service.ProductService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ProductDatasource {
    suspend fun postAddProduct(addProductRequest: AddProductRequest): BaseResponse<ProductResponse>
    suspend fun getAllProduct(): Flow<PagingData<ProductParamResponse>>
    suspend fun getDetailProduct(idProduct: Int): BaseResponse<ProductResponse>
    suspend fun updateProduct(idProduct: Int, productRequest: ProductRequest): BaseResponse<ProductResponse>
    suspend fun deleteProduct(idProduct: Int): BaseResponse<ProductResponse>
}

class ProductDatasourceImpl @Inject constructor(
    private val productService: ProductService
): ProductDatasource {
    override suspend fun postAddProduct(addProductRequest: AddProductRequest): BaseResponse<ProductResponse> {
        return productService.postAddProduct(addProductRequest)
    }

    override suspend fun getAllProduct(): Flow<PagingData<ProductParamResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PagingProductDatasource(productService)
            }
        ).flow
    }

    override suspend fun getDetailProduct(idProduct: Int): BaseResponse<ProductResponse> {
        return productService.getDetailProduct(idProduct)
    }

    override suspend fun updateProduct(idProduct: Int, productRequest: ProductRequest): BaseResponse<ProductResponse> {
        return productService.updateProduct(idProduct, productRequest)
    }

    override suspend fun deleteProduct(idProduct: Int): BaseResponse<ProductResponse> {
        return productService.deleteProduct(idProduct)
    }
}
