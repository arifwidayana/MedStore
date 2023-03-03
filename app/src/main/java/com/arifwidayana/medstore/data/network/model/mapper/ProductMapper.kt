package com.arifwidayana.medstore.data.network.model.mapper

import com.arifwidayana.medstore.common.utils.mapper.DataObjectMapper
import com.arifwidayana.medstore.common.utils.mapper.ListMapper
import com.arifwidayana.medstore.common.utils.mapper.ViewParamMapper
import com.arifwidayana.medstore.data.network.model.request.product.ProductParamRequest
import com.arifwidayana.medstore.data.network.model.request.product.ProductRequest
import com.arifwidayana.medstore.data.network.model.response.product.ProductParamResponse
import com.arifwidayana.medstore.data.network.model.response.product.ProductResponse

object ProductListResponseMapper : ViewParamMapper<List<ProductResponse>, List<ProductParamResponse>> {
    override fun toViewParam(dataObject: List<ProductResponse>?): List<ProductParamResponse> =
        ListMapper(ProductResponseMapper).toViewParams(dataObject)
}

object ProductResponseMapper: ViewParamMapper<ProductResponse, ProductParamResponse> {
    override fun toViewParam(dataObject: ProductResponse?): ProductParamResponse =
        ProductParamResponse(
            id = dataObject?.id ?: 0,
            productName = dataObject?.productName.orEmpty(),
            price = dataObject?.price ?: 0,
            stock = dataObject?.stock ?: 0,
            supplier = SupplierViewParamMapper.toViewParam(dataObject?.supplier)
        )
}

object SupplierViewParamMapper : ViewParamMapper<ProductResponse.Supplier, ProductParamResponse.Supplier> {
    override fun toViewParam(dataObject: ProductResponse.Supplier?): ProductParamResponse.Supplier =
        ProductParamResponse.Supplier(
            id = dataObject?.id ?: 0,
            supplierName = dataObject?.supplierName.orEmpty(),
            phoneNumber = dataObject?.phoneNumber.orEmpty(),
            address = dataObject?.address.orEmpty()
        )
}

object ProductRequestMapper: DataObjectMapper<ProductRequest, ProductParamRequest> {
    override fun toDataObject(viewParam: ProductParamRequest?): ProductRequest =
        ProductRequest(
            id = viewParam?.id,
            productName = viewParam?.productName,
            price = viewParam?.price,
            stock = viewParam?.stock,
            supplier = SupplierDataObjectMapper.toDataObject(viewParam?.supplier)
        )
}

object SupplierDataObjectMapper : DataObjectMapper<ProductRequest.Supplier, ProductParamRequest.Supplier> {
    override fun toDataObject(viewParam: ProductParamRequest.Supplier?): ProductRequest.Supplier =
        ProductRequest.Supplier(
            supplierName = viewParam?.supplierName.orEmpty(),
            phoneNumber = viewParam?.phoneNumber.orEmpty(),
            address = viewParam?.address.orEmpty()
        )
}