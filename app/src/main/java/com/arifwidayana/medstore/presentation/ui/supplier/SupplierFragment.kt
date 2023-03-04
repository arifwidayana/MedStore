package com.arifwidayana.medstore.presentation.ui.supplier

import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.arifwidayana.medstore.R
import com.arifwidayana.medstore.common.base.BaseFragment
import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.databinding.FragmentSupplierBinding
import com.arifwidayana.medstore.presentation.adapter.SupplierAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SupplierFragment : BaseFragment<FragmentSupplierBinding, SupplierViewModel>(
    FragmentSupplierBinding::inflate
) {
    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        viewModel.getSupplier()
    }

    private fun onClick() {
        binding.apply {
            fabAddSupplier.setOnClickListener {
                moveNav(R.id.action_supplierFragment_to_addSupplierFragment)
            }
        }
    }

    override fun observeData() {
        lifecycleScope.launchWhenCreated {
            viewModel.getSupplierResult.asLiveData().observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Empty -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        setStateSupplier(it.data)
                    }
                    is Resource.Error -> {
                        showMessageSnackBar(true, exception = it.exception, message = it.message)
                    }
                }
            }
        }
    }

    private fun setStateSupplier(data: SupplierDataResult?) {
        binding.apply {
            data?.let {
                val adapter = SupplierAdapter { res ->
                    moveNav(
                        SupplierFragmentDirections.actionSupplierFragmentToDetailSupplierFragment()
                            .setId(res)
                    )
                }
                adapter.submitData(lifecycle, it)
                rvSupplier.adapter = adapter
            }
        }
    }
}