package com.arifwidayana.medstore.presentation.ui.supplier.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.network.model.mapper.SupplierRequestMapper
import com.arifwidayana.medstore.data.network.model.mapper.SupplierResponseMapper
import com.arifwidayana.medstore.data.network.model.request.supplier.SupplierParamRequest
import com.arifwidayana.medstore.data.network.model.response.supplier.SupplierParamResponse
import com.arifwidayana.medstore.data.network.repository.SupplierRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailSupplierViewModel @Inject constructor(
    private val supplierRepository: SupplierRepository
) : DetailSupplierContract, ViewModel() {
    private val _getSupplierResult = MutableStateFlow<Resource<SupplierParamResponse>>(Resource.Empty())
    private val _updateSupplierResult = MutableStateFlow<Resource<Unit>>(Resource.Empty())
    private val _deleteSupplierResult = MutableStateFlow<Resource<Unit>>(Resource.Empty())
    override val getSupplierResult: StateFlow<Resource<SupplierParamResponse>> = _getSupplierResult
    override val updateSupplierResult: StateFlow<Resource<Unit>> = _updateSupplierResult
    override val deleteSupplierResult: StateFlow<Resource<Unit>> = _deleteSupplierResult

    override fun getSupplier(idProduct: Int) {
        viewModelScope.launch {
            _getSupplierResult.value = Resource.Loading()
            supplierRepository.getDetailSupplier(idProduct).collect {
                when (it) {
                    is Resource.Success -> {
                        _getSupplierResult.value = Resource.Success(
                            SupplierResponseMapper.toViewParam(it.data?.metadata)
                        )
                    }
                    is Resource.Error -> {
                        _getSupplierResult.value = Resource.Error(
                            exception= it.exception,
                            message = it.data?.message
                        )
                    }
                    else -> {}
                }
            }
        }
    }

    override fun updateSupplier(idProduct: Int, supplierParamRequest: SupplierParamRequest) {
        viewModelScope.launch {
            _updateSupplierResult.value = Resource.Loading()
            supplierRepository.updateSupplier(
                idProduct = idProduct,
                supplierRequest = SupplierRequestMapper.toDataObject(supplierParamRequest)
            ).collect {
                when (it) {
                    is Resource.Success -> {
                        _updateSupplierResult.value = Resource.Success(message = it.data?.message)
                    }
                    is Resource.Error -> {
                        _updateSupplierResult.value = Resource.Error(
                            exception= it.exception,
                            message = it.data?.message
                        )
                    }
                    else -> {}
                }
            }
        }
    }

    override fun deleteSupplier(idProduct: Int) {
        viewModelScope.launch {
            _deleteSupplierResult.value = Resource.Loading()
            supplierRepository.deleteSupplier(idProduct).collect {
                when (it) {
                    is Resource.Success -> {
                        _deleteSupplierResult.value = Resource.Success(message = it.data?.message)
                    }
                    is Resource.Error -> {
                        _deleteSupplierResult.value = Resource.Error(
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