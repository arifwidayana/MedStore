package com.arifwidayana.medstore.presentation.ui.supplier

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.network.repository.SupplierRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SupplierViewModel @Inject constructor(
    private val supplierRepository: SupplierRepository
): SupplierContract, ViewModel() {
    private val _getSupplierResult = MutableStateFlow<Resource<SupplierDataResult>>(Resource.Empty())
    override val getSupplierResult: StateFlow<Resource<SupplierDataResult>> = _getSupplierResult

    override fun getSupplier() {
        viewModelScope.launch {
            _getSupplierResult.value = Resource.Loading()
            supplierRepository.getAllProduct().collect {
                _getSupplierResult.value = Resource.Success(it)
            }
        }
    }
}