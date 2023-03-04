package com.arifwidayana.medstore.data.network.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arifwidayana.medstore.common.utils.Constant.DEFAULT_INDEX_PAGE
import com.arifwidayana.medstore.data.network.model.mapper.SupplierListResponseMapper
import com.arifwidayana.medstore.data.network.model.response.supplier.SupplierParamResponse
import com.arifwidayana.medstore.data.network.service.SupplierService
import retrofit2.HttpException
import java.io.IOException

class PagingSupplierDatasource(
    private val supplierService: SupplierService
): PagingSource<Int, SupplierParamResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SupplierParamResponse> {
        return try {
            val pageIndex = params.key ?: DEFAULT_INDEX_PAGE
            val response = supplierService.getAllSupplier(
                size = 20,
                page = pageIndex
            )
            val result = SupplierListResponseMapper.toViewParam(response.metadata)
            LoadResult.Page(
                data = result,
                prevKey = if (pageIndex == DEFAULT_INDEX_PAGE) null else pageIndex - 1,
                nextKey = if (result.isEmpty()) null else pageIndex + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SupplierParamResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}