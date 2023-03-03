package com.arifwidayana.medstore.presentation.ui.splash

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.lifecycleScope
import com.arifwidayana.medstore.R
import com.arifwidayana.medstore.common.base.BaseFragment
import com.arifwidayana.medstore.common.wrapper.Resource
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
        viewModel.getToken()
    }

    override fun observeData() {
        lifecycleScope.launchWhenCreated {
            viewModel.getTokenResult.collect {
                if (it is Resource.Success) {
                    when(it.data) {
                        true -> {
                            Handler(Looper.getMainLooper()).postDelayed({
                                moveNav(R.id.action_splashFragment_to_loginFragment)
                            }, 3000)
                        }
                        else -> {
                            Handler(Looper.getMainLooper()).postDelayed({
                                moveNav(R.id.action_splashFragment_to_loginFragment)
                            }, 3000)
                        }
                    }
                }
            }
        }
    }
}