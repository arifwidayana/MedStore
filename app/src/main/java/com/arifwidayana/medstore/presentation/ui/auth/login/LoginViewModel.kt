package com.arifwidayana.medstore.presentation.ui.auth.login

import androidx.lifecycle.ViewModel
import com.arifwidayana.medstore.data.network.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
): LoginContract, ViewModel() {
}