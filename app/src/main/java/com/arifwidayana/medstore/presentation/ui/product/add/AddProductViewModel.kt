package com.arifwidayana.medstore.presentation.ui.product.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.network.model.mapper.AddProductRequestMapper
import com.arifwidayana.medstore.data.network.model.request.product.add.AddProductParamRequest
import com.arifwidayana.medstore.data.network.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : AddProductContract, ViewModel() {
    private val _addProductResult = MutableStateFlow<Resource<Unit>>(Resource.Empty())
    override val addProductResult: StateFlow<Resource<Unit>> = _addProductResult

    override fun addProduct(addProductParamRequest: AddProductParamRequest) {
        viewModelScope.launch {
            _addProductResult.value = Resource.Loading()
            productRepository.postAddProduct(
                AddProductRequestMapper.toDataObject(addProductParamRequest)
            ).collect {
                when (it) {
                    is Resource.Success -> {
                        _addProductResult.value = Resource.Success(message = it.data?.message)
                    }
                    is Resource.Error -> {
                        _addProductResult.value = Resource.Error(
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