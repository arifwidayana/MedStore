package com.arifwidayana.medstore.presentation.ui.auth.register

import androidx.lifecycle.lifecycleScope
import com.arifwidayana.medstore.common.base.BaseFragment
import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.network.model.request.auth.register.RegisterParamRequest
import com.arifwidayana.medstore.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>(
    FragmentRegisterBinding::inflate
) {
    override fun initView() {
        onClick()
    }

    private fun onClick() {
        binding.apply {
            btnBack.setOnClickListener {
                moveNavigateUp()
            }
            btnRegister.setOnClickListener {
                viewModel.registerUser(
                    RegisterParamRequest(
                        name = etName.text.toString(),
                        username = etUsername.text.toString(),
                        password = etPassword.text.toString()
                    )
                )
            }
        }
    }

    override fun observeData() {
        lifecycleScope.launchWhenCreated {
            viewModel.registerUserResult.collect {
                when (it) {
                    is Resource.Empty -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        if (it.data == true) {
                            moveNavigateUp()
                        }
                        showMessageSnackBar(true, it.message)
                    }
                    is Resource.Error -> {
                        showMessageSnackBar(true, exception = it.exception)
                    }
                }
            }
        }
    }
}