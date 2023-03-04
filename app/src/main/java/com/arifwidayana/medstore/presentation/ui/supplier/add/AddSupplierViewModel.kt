package com.arifwidayana.medstore.presentation.ui.supplier.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.network.model.mapper.SupplierRequestMapper
import com.arifwidayana.medstore.data.network.model.request.supplier.SupplierParamRequest
import com.arifwidayana.medstore.data.network.repository.SupplierRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddSupplierViewModel @Inject constructor(
    private val supplierRepository: SupplierRepository
) : AddSupplierContract, ViewModel() {
    private val _addSupplierResult = MutableStateFlow<Resource<Unit>>(Resource.Empty())
    override val addSupplierResult: StateFlow<Resource<Unit>> = _addSupplierResult

    override fun addSupplier(supplierParamRequest: SupplierParamRequest) {
        viewModelScope.launch {
            _addSupplierResult.value = Resource.Loading()
            supplierRepository.postAddSupplier(
                SupplierRequestMapper.toDataObject(supplierParamRequest)
            ).collect {
                when (it) {
                    is Resource.Success -> {
                        _addSupplierResult.value = Resource.Success(message = it.data?.message)
                    }
                    is Resource.Error -> {
                        _addSupplierResult.value = Resource.Error(
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