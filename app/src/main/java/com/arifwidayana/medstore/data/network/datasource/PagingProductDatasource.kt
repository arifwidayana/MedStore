package com.arifwidayana.medstore.data.network.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arifwidayana.medstore.common.utils.Constant.DEFAULT_INDEX_PAGE
import com.arifwidayana.medstore.data.network.model.mapper.ProductListResponseMapper
import com.arifwidayana.medstore.data.network.model.response.product.ProductParamResponse
import com.arifwidayana.medstore.data.network.service.ProductService
import retrofit2.HttpException
import java.io.IOException

class PagingProductDatasource(
    private val productService: ProductService
): PagingSource<Int, ProductParamResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductParamResponse> {
        return try {
            val pageIndex = params.key ?: DEFAULT_INDEX_PAGE
            val response = productService.getAllProduct(
                size = 20,
                page = pageIndex
            )
            val result = ProductListResponseMapper.toViewParam(response.metadata)
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

    override fun getRefreshKey(state: PagingState<Int, ProductParamResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}