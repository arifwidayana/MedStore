package com.arifwidayana.medstore.presentation.ui.product

import androidx.lifecycle.lifecycleScope
import com.arifwidayana.medstore.common.base.BaseFragment
import com.arifwidayana.medstore.databinding.FragmentProductBinding

class ProductFragment : BaseFragment<FragmentProductBinding, ProductViewModel>(
    FragmentProductBinding::inflate
) {
    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
//        viewModel
    }

    private fun onClick() {
        binding.apply {

        }
    }

    override fun observeData() {
//        lifecycleScope.launchWhenCreated {
//
//        }
    }
}