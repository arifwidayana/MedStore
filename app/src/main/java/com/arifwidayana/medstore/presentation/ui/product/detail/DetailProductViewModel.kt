package com.arifwidayana.medstore.presentation.ui.product.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.network.model.mapper.ProductRequestMapper
import com.arifwidayana.medstore.data.network.model.mapper.ProductResponseMapper
import com.arifwidayana.medstore.data.network.model.request.product.ProductParamRequest
import com.arifwidayana.medstore.data.network.model.response.product.ProductParamResponse
import com.arifwidayana.medstore.data.network.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : DetailProductContract, ViewModel() {
    private val _getProductResult =
        MutableStateFlow<Resource<ProductParamResponse>>(Resource.Empty())
    private val _updateProductResult = MutableStateFlow<Resource<Boolean>>(Resource.Empty())
    private val _deleteProductResult = MutableStateFlow<Resource<Boolean>>(Resource.Empty())
    override val getProductResult: StateFlow<Resource<ProductParamResponse>> = _getProductResult
    override val updateProductResult: StateFlow<Resource<Boolean>> = _updateProductResult
    override val deleteProductResult: StateFlow<Resource<Boolean>> = _deleteProductResult

    override fun getProduct(idProduct: Int) {
        viewModelScope.launch {
            _getProductResult.value = Resource.Loading()
            productRepository.getDetailProduct(idProduct).collect {
                when (it) {
                    is Resource.Success -> {
                        _getProductResult.value = Resource.Success(
                            ProductResponseMapper.toViewParam(it.data?.metadata)
                        )
                    }
                    is Resource.Error -> {
                        _getProductResult.value = Resource.Error(
                            exception= it.exception,
                            message = it.data?.message
                        )
                    }
                    else -> {}
                }
            }
        }
    }

    override fun updateProduct(idProduct: Int, productParamRequest: ProductParamRequest) {
        viewModelScope.launch {
            _updateProductResult.value = Resource.Loading()
            productRepository.updateProduct(
                idProduct = idProduct,
                productRequest = ProductRequestMapper.toDataObject(productParamRequest)
            ).collect {
                when (it) {
                    is Resource.Success -> {
                        _updateProductResult.value = Resource.Success(true, it.data?.message)
                    }
                    is Resource.Error -> {
                        _updateProductResult.value = Resource.Error(
                            exception= it.exception,
                            message = it.data?.message
                        )
                    }
                    else -> {}
                }
            }
        }
    }

    override fun deleteProduct(idProduct: Int) {
        viewModelScope.launch {
            _deleteProductResult.value = Resource.Loading()
            productRepository.deleteProduct(idProduct).collect {
                when (it) {
                    is Resource.Success -> {
                        _deleteProductResult.value = Resource.Success(true, it.data?.message)
                    }
                    is Resource.Error -> {
                        _deleteProductResult.value = Resource.Error(
                            exception= it.exception,
                            message = it.data?.message
                        )
                    }
                    else -> {}
                }
            }
        }
    }
}