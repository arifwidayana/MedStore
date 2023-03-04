package com.arifwidayana.medstore.data.network.service

import com.arifwidayana.medstore.BuildConfig
import com.arifwidayana.medstore.common.base.BaseResponse
import com.arifwidayana.medstore.common.utils.Constant
import com.arifwidayana.medstore.data.local.repository.UserPreferenceRepository
import com.arifwidayana.medstore.data.network.model.request.supplier.SupplierRequest
import com.arifwidayana.medstore.data.network.model.response.supplier.SupplierResponse
import com.chuckerteam.chucker.api.ChuckerInterceptor
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface SupplierService {
    @POST("supplier/create")
    suspend fun postAddSupplier(@Body supplierRequest: SupplierRequest): BaseResponse<SupplierResponse>

    @GET("supplier/find-all")
    suspend fun getAllSupplier(
        @Query("limit") size: Int,
        @Query("offset") page: Int
    ): BaseResponse<List<SupplierResponse>>

    @GET("supplier/find-by-id/{id}")
    suspend fun getDetailSupplier(
        @Path(Constant.ID_PATH) idProduct: Int
    ): BaseResponse<SupplierResponse>

    @PUT("supplier/update/{id}")
    suspend fun updateSupplier(
        @Path(Constant.ID_PATH) idProduct: Int,
        @Body supplierRequest: SupplierRequest
    ): BaseResponse<SupplierResponse>

    @DELETE("supplier/delete/{id}")
    suspend fun deleteSupplier(
        @Path(Constant.ID_PATH) idProduct: Int
    ): BaseResponse<SupplierResponse>

    companion object {
        @JvmStatic
        operator fun invoke(
            chuckerInterceptor: ChuckerInterceptor,
            userPreferenceRepository: UserPreferenceRepository
        ): SupplierService {
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
            return retrofit.create(SupplierService::class.java)
        }
    }
}