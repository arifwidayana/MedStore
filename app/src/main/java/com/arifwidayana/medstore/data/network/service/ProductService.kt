package com.arifwidayana.medstore.data.network.service

import com.arifwidayana.medstore.BuildConfig
import com.arifwidayana.medstore.common.base.BaseResponse
import com.arifwidayana.medstore.common.utils.Constant.ID_PATH
import com.arifwidayana.medstore.data.local.repository.UserPreferenceRepository
import com.arifwidayana.medstore.data.network.model.request.product.ProductRequest
import com.arifwidayana.medstore.data.network.model.response.product.ProductResponse
import com.chuckerteam.chucker.api.ChuckerInterceptor
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ProductService {
    @POST("barang/create")
    suspend fun postAddProduct(productRequest: ProductRequest): BaseResponse<ProductResponse>

    @GET("barang/find-all")
    suspend fun getAllProduct(
        @Query("limit") size: Int,
        @Query("offset") page: Int
    ): BaseResponse<List<ProductResponse>>

    @GET("barang/find-by-id/{id}")
    suspend fun getDetailProduct(
        @Path(ID_PATH) idProduct: Int
    ): BaseResponse<ProductResponse>

    @PUT("barang/update/{id}")
    suspend fun updateProduct(
        @Path(ID_PATH) idProduct: Int,
        @Body productRequest: ProductRequest
    ): BaseResponse<ProductResponse>

    @DELETE("barang/delete/{id}")
    suspend fun deleteProduct(
        @Path(ID_PATH) idProduct: Int
    ): BaseResponse<ProductResponse>

    companion object {
        @JvmStatic
        operator fun invoke(
            chuckerInterceptor: ChuckerInterceptor,
            userPreferenceRepository: UserPreferenceRepository
        ): ProductService {
            val authInterceptor = Interceptor {
                val requestBuilder = it.request().newBuilder()
                runBlocking {
                    userPreferenceRepository.getToken().first { tokenResponse ->
                        val token = tokenResponse.data
                        if (!token.isNullOrEmpty()) {
                            requestBuilder.addHeader(
                                name = "Authorization",
                                value = "Bearer $token"
                            )
                        }
                        true
                    }
                }
                it.proceed(requestBuilder.build())
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(chuckerInterceptor)
                .addInterceptor(authInterceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(ProductService::class.java)
        }
    }
}