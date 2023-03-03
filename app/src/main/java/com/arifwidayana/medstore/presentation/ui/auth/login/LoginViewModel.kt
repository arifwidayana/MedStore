package com.arifwidayana.medstore.presentation.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.local.repository.UserPreferenceRepository
import com.arifwidayana.medstore.data.network.model.mapper.LoginRequestMapper
import com.arifwidayana.medstore.data.network.model.request.auth.login.LoginParamRequest
import com.arifwidayana.medstore.data.network.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userPreferenceRepository: UserPreferenceRepository
): LoginContract, ViewModel() {
    private val _loginUserResult = MutableStateFlow<Resource<LoginDataResult>>(Resource.Empty())
    override val loginUserResult: StateFlow<Resource<LoginDataResult>> = _loginUserResult

    override fun loginUser(loginParamRequest: LoginParamRequest) {
        viewModelScope.launch {
            _loginUserResult.value = Resource.Loading()
            authRepository.loginUser(LoginRequestMapper.toDataObject(loginParamRequest)).collect {
                try {
                    if (it.data?.metadata != null) {
                        userPreferenceRepository.setToken(it.data.metadata.token.toString()).first()
                        _loginUserResult.value = Resource.Success(it.data.metadata)
                    } else {
                        _loginUserResult.value = Resource.Error(message = it.data?.message)
                    }
                } catch (e: Exception) {
                    _loginUserResult.value = Resource.Error(e)
                }
            }
        }
    }
}