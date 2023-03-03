package com.arifwidayana.medstore.di

import android.content.Context
import androidx.room.Room
import com.arifwidayana.medstore.common.base.BaseGenericViewModel
import com.arifwidayana.medstore.data.local.datasource.UserPreferenceDatasource
import com.arifwidayana.medstore.data.local.datasource.UserPreferenceDatasourceImpl
import com.arifwidayana.medstore.data.local.repository.UserPreferenceRepository
import com.arifwidayana.medstore.data.local.repository.UserPreferenceRepositoryImpl
import com.arifwidayana.medstore.data.network.datasource.AuthDatasource
import com.arifwidayana.medstore.data.network.datasource.AuthDatasourceImpl
import com.arifwidayana.medstore.data.network.repository.AuthRepository
import com.arifwidayana.medstore.data.network.repository.AuthRepositoryImpl
import com.arifwidayana.medstore.data.network.service.AuthService
import com.arifwidayana.medstore.presentation.ui.auth.login.LoginViewModel
import com.arifwidayana.medstore.presentation.ui.auth.register.RegisterViewModel
import com.arifwidayana.medstore.presentation.ui.splash.SplashViewModel
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

object MainModule {
    @Module
    @InstallIn(SingletonComponent::class)
    object NetworkModule {
        @Provides
        @Singleton
        fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
            return ChuckerInterceptor.Builder(context).build()
        }

        @Provides
        @Singleton
        fun provideAuthService(chuckerInterceptor: ChuckerInterceptor): AuthService {
            return AuthService.invoke(chuckerInterceptor)
        }

//        @Provides
//        @Singleton
//        fun provideInfoService(chuckerInterceptor: ChuckerInterceptor): InfoService {
//            return InfoService.invoke(chuckerInterceptor)
//        }
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object DataSourceModule {
        @Provides
        @Singleton
        fun provideUserPreferenceDatasource(@ApplicationContext context: Context): UserPreferenceDatasource {
            return UserPreferenceDatasourceImpl(context)
        }

        @Provides
        @Singleton
        fun provideAuthDatasource(authService: AuthService): AuthDatasource {
            return AuthDatasourceImpl(authService)
        }

//        @Provides
//        @Singleton
//        fun provideInfoDatasource(infoService: InfoService): InfoDatasource {
//            return InfoDatasourceImpl(infoService)
//        }
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object RepositoryModule {
        @Provides
        @Singleton
        fun provideUserPreferenceRepository(userPreferenceDatasource: UserPreferenceDatasource): UserPreferenceRepository {
            return UserPreferenceRepositoryImpl(userPreferenceDatasource)
        }

        @Provides
        @Singleton
        fun provideAuthRepository(authDatasource: AuthDatasource): AuthRepository {
            return AuthRepositoryImpl(authDatasource)
        }
    }

    @Module
    @InstallIn(FragmentComponent::class)
    object ViewModelModule {
        @Provides
        @FragmentScoped
        fun provideSplashViewModel(userPreferenceRepository: UserPreferenceRepository): SplashViewModel {
            return BaseGenericViewModel(SplashViewModel(userPreferenceRepository)).create(
                SplashViewModel::class.java
            )
        }

        @Provides
        @FragmentScoped
        fun provideLoginViewModel(
            authRepository: AuthRepository,
            userPreferenceRepository: UserPreferenceRepository
        ): LoginViewModel {
            return BaseGenericViewModel(
                LoginViewModel(
                    authRepository,
                    userPreferenceRepository
                )
            ).create(
                LoginViewModel::class.java
            )
        }

        @Provides
        @FragmentScoped
        fun provideRegisterViewModel(authRepository: AuthRepository): RegisterViewModel {
            return BaseGenericViewModel(RegisterViewModel(authRepository)).create(
                RegisterViewModel::class.java
            )
        }

//        @Provides
//        @FragmentScoped
//        fun provideFavoriteViewModel(favoriteRepository: FavoriteRepository): FavoriteViewModel {
//            return BaseGenericViewModel(FavoriteViewModel(favoriteRepository)).create(
//                FavoriteViewModel::class.java
//            )
//        }
    }
}