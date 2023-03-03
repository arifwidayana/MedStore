package com.arifwidayana.medstore.presentation.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.medstore.common.utils.Constant.USERNAME_IS_REGISTERED
import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.network.model.mapper.RegisterRequestMapper
import com.arifwidayana.medstore.data.network.model.request.auth.register.RegisterParamRequest
import com.arifwidayana.medstore.data.network.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
): RegisterContract, ViewModel() {
    private val _registerUserResult = MutableStateFlow<RegisterDataResult>(Resource.Empty())
    override val registerUserResult: StateFlow<RegisterDataResult> = _registerUserResult

    override fun registerUser(registerParamRequest: RegisterParamRequest) {
        viewModelScope.launch {
            _registerUserResult.value = Resource.Loading()
            authRepository.registerUser(RegisterRequestMapper.toDataObject(registerParamRequest)).collect {
                try {
                    _registerUserResult.value = Resource.Success(
                        data = it.data?.message != USERNAME_IS_REGISTERED,
                        message = it.data?.message
                    )
                } catch (e: Exception) {
                    _registerUserResult.value = Resource.Error(e)
                }
            }
        }
    }
}