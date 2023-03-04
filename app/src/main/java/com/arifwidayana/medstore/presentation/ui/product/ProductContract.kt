package com.arifwidayana.medstore.presentation.ui.product

import androidx.paging.PagingData
import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.network.model.response.product.ProductParamResponse
import kotlinx.coroutines.flow.StateFlow

typealias ProductDataResult = PagingData<ProductParamResponse>

interface ProductContract {
    val getProductResult: StateFlow<Resource<ProductDataResult>>
    fun getProduct()
}