package com.arifwidayana.medstore.data.network.model.mapper

import com.arifwidayana.medstore.common.utils.mapper.DataObjectMapper
import com.arifwidayana.medstore.common.utils.mapper.ListMapper
import com.arifwidayana.medstore.common.utils.mapper.ViewParamMapper
import com.arifwidayana.medstore.data.network.model.request.supplier.SupplierParamRequest
import com.arifwidayana.medstore.data.network.model.request.supplier.SupplierRequest
import com.arifwidayana.medstore.data.network.model.response.supplier.SupplierParamResponse
import com.arifwidayana.medstore.data.network.model.response.supplier.SupplierResponse

object SupplierRequestMapper: DataObjectMapper<SupplierRequest, SupplierParamRequest> {
    override fun toDataObject(viewParam: SupplierParamRequest?): SupplierRequest =
        SupplierRequest(
            supplierName = viewParam?.supplierName,
            supplierAddress = viewParam?.supplierAddress,
            supplierPhoneNumber = viewParam?.supplierPhoneNumber
        )
}

object SupplierListResponseMapper: ViewParamMapper<List<SupplierResponse>, List<SupplierParamResponse>> {
    override fun toViewParam(dataObject: List<SupplierResponse>?): List<SupplierParamResponse> =
        ListMapper(SupplierResponseMapper).toViewParams(dataObject)
}

object SupplierResponseMapper: ViewParamMapper<SupplierResponse, SupplierParamResponse> {
    override fun toViewParam(dataObject: SupplierResponse?): SupplierParamResponse =
        SupplierParamResponse(
            supplierId = dataObject?.supplierId ?: 0,
            supplierName = dataObject?.supplierName.orEmpty(),
            supplierAddress = dataObject?.supplierAddress.orEmpty(),
            supplierPhoneNumber = dataObject?.supplierPhoneNumber.orEmpty()
        )
}