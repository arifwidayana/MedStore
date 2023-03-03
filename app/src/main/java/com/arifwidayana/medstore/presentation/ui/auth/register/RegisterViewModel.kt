package com.arifwidayana.medstore.presentation.ui.auth.register

import androidx.lifecycle.ViewModel
import com.arifwidayana.medstore.data.network.repository.AuthRepository
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
): RegisterContract, ViewModel() {
}