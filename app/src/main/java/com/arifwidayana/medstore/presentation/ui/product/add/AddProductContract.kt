package com.arifwidayana.medstore.presentation.ui.product.add

import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.network.model.request.product.ProductParamRequest
import com.arifwidayana.medstore.data.network.model.request.product.add.AddProductParamRequest
import kotlinx.coroutines.flow.StateFlow

interface AddProductContract {
    val addProductResult: StateFlow<Resource<Unit>>
    fun addProduct(addProductParamRequest: AddProductParamRequest)
}