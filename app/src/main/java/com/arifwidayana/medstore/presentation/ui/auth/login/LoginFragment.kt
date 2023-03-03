package com.arifwidayana.medstore.presentation.ui.auth.login

import androidx.lifecycle.lifecycleScope
import com.arifwidayana.medstore.R
import com.arifwidayana.medstore.common.base.BaseFragment
import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.network.model.request.auth.login.LoginParamRequest
import com.arifwidayana.medstore.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(
    FragmentLoginBinding::inflate
) {
    override fun initView() {
        onClick()
    }

    private fun onClick() {
        binding.apply {
            btnLogin.setOnClickListener {
                viewModel.loginUser(
                    LoginParamRequest(
                        username = etUsername.text.toString(),
                        password = etPassword.text.toString()
                    )
                )
            }
            tvRegister.setOnClickListener {
                moveNav(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }

    override fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModel.loginUserResult.collect {
                when(it) {
                    is Resource.Empty -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        moveNav(R.id.action_loginFragment_to_registerFragment)
                    }
                    is Resource.Error -> {
                        showMessageSnackBar(true, exception = it.exception )
                    }
                }
            }
        }
    }
}