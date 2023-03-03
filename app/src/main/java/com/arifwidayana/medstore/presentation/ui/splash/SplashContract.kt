package com.arifwidayana.medstore.presentation.ui.splash

import com.arifwidayana.medstore.common.wrapper.Resource
import kotlinx.coroutines.flow.StateFlow

interface SplashContract {
    val getTokenResult: StateFlow<Resource<Boolean>>
    fun getToken()
}