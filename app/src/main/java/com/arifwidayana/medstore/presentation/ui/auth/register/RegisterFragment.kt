package com.arifwidayana.medstore.presentation.ui.auth.register

import com.arifwidayana.medstore.common.base.BaseFragment
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

    }

    override fun observeData() {

    }
}