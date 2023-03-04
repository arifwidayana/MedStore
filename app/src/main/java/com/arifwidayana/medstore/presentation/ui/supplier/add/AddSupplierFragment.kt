package com.arifwidayana.medstore.presentation.ui.supplier.add

import androidx.lifecycle.lifecycleScope
import com.arifwidayana.medstore.common.base.BaseFragment
import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.network.model.request.supplier.SupplierParamRequest
import com.arifwidayana.medstore.databinding.FragmentAddSupplierBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddSupplierFragment : BaseFragment<FragmentAddSupplierBinding, AddSupplierViewModel>(
    FragmentAddSupplierBinding::inflate
) {
    override fun initView() {
        onClick()
    }

    private fun onClick() {
        binding.apply {
            sivBack.setOnClickListener {
                moveNavigateUp()
            }
            btnSubmit.setOnClickListener {
                viewModel.addSupplier(
                    supplierParamRequest = SupplierParamRequest(
                        supplierName = etSupplierName.text.toString(),
                        supplierAddress = etSupplierAddress.text.toString(),
                        supplierPhoneNumber = etSupplierPhoneNumber.text.toString()
                    )
                )
            }
        }
    }

    override fun observeData() {
        lifecycleScope.launchWhenCreated {
            viewModel.addSupplierResult.collect {
                when (it) {
                    is Resource.Empty -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        moveNavigateUp()
                        showMessageSnackBar(true, message = it.message)
                    }
                    is Resource.Error -> {
                        showMessageSnackBar(true, exception = it.exception, message = it.message)
                    }
                }
            }
        }
    }
}