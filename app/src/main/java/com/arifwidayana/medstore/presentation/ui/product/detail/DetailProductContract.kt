package com.arifwidayana.medstore.presentation.ui.product.detail

import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.network.model.request.product.ProductParamRequest
import com.arifwidayana.medstore.data.network.model.response.product.ProductParamResponse
import kotlinx.coroutines.flow.StateFlow

interface DetailProductContract {
    val getProductResult : StateFlow<Resource<ProductParamResponse>>
    val updateProductResult : StateFlow<Resource<Boolean>>
    val deleteProductResult : StateFlow<Resource<Boolean>>
    fun getProduct(idProduct: Int)
    fun updateProduct(idProduct: Int, productParamRequest: ProductParamRequest)
    fun deleteProduct(idProduct: Int)
}