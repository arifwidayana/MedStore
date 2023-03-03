package com.arifwidayana.medstore.data.network.service

import com.arifwidayana.medstore.BuildConfig
import com.arifwidayana.medstore.common.base.BaseResponse
import com.arifwidayana.medstore.data.network.model.request.auth.login.LoginRequest
import com.arifwidayana.medstore.data.network.model.request.auth.register.RegisterRequest
import com.arifwidayana.medstore.data.network.model.response.auth.login.LoginResponse
import com.arifwidayana.medstore.data.network.model.response.auth.register.RegisterResponse
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface AuthService {
    @POST("auth/login")
    suspend fun loginUser(loginRequest: LoginRequest): BaseResponse<LoginResponse>

    @POST("auth/register")
    suspend fun registerUser(registerRequest: RegisterRequest): BaseResponse<RegisterResponse>

    companion object {
        @JvmStatic
        operator fun invoke(chuckerInterceptor : ChuckerInterceptor): AuthService{
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(chuckerInterceptor)
//                .addInterceptor {
//                    val req = it.request()
//                    val query = req.url
//                        .newBuilder()
//                        .addQueryParameter(API_NAME, BuildConfig.API_KEY)
//                        .build()
//                    return@addInterceptor it.proceed(req.newBuilder().url(query).build())
//                }
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(AuthService::class.java)
        }
    }
}