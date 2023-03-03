package com.arifwidayana.medstore.presentation.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.local.repository.UserPreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userPreferenceRepository: UserPreferenceRepository
): SplashContract, ViewModel() {
    private val _getTokenResult = MutableStateFlow<Resource<Boolean>>(Resource.Empty())
    override val getTokenResult: StateFlow<Resource<Boolean>> = _getTokenResult

    override fun getToken() {
        viewModelScope.launch {
            userPreferenceRepository.getToken().collect {
                _getTokenResult.value = Resource.Success(it.data?.isNotEmpty())
            }
        }
    }
}