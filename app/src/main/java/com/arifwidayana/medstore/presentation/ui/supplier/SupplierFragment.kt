package com.arifwidayana.medstore.presentation.ui.supplier

import androidx.lifecycle.lifecycleScope
import com.arifwidayana.medstore.common.base.BaseFragment
import com.arifwidayana.medstore.databinding.FragmentSupplierBinding

class SupplierFragment : BaseFragment<FragmentSupplierBinding, SupplierViewModel>(
    FragmentSupplierBinding::inflate
) {
    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        viewModel
    }

    private fun onClick() {
        binding.apply {

        }
    }

    override fun observeData() {
        lifecycleScope.launchWhenCreated {

        }
    }
}