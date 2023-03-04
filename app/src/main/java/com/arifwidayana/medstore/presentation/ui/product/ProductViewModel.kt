package com.arifwidayana.medstore.presentation.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.network.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ProductContract, ViewModel() {
    private val _getProductResult = MutableStateFlow<Resource<ProductDataResult>>(Resource.Empty())
    override val getProductResult: StateFlow<Resource<ProductDataResult>> = _getProductResult

    override fun getProduct() {
        viewModelScope.launch {
            _getProductResult.value = Resource.Loading()
            productRepository.getAllProduct().collect {
                _getProductResult.value = Resource.Success(it)
            }
        }
    }
}