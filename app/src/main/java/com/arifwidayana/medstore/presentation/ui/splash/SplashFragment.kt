package com.arifwidayana.medstore.presentation.ui.splash

import com.arifwidayana.medstore.common.base.BaseFragment
import com.arifwidayana.medstore.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(
    FragmentSplashBinding::inflate
) {
    override fun initView() {
        onView()
    }

    private fun onView() {
        viewModel
    }

    override fun observeData() {
    }
}