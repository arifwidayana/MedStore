package com.arifwidayana.medstore.presentation.ui.auth.login

import com.arifwidayana.medstore.common.base.BaseFragment
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

        }
    }

    override fun observeData() {
    }
}