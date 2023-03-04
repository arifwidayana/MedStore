package com.arifwidayana.medstore.presentation.ui.supplier.detail

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.arifwidayana.medstore.common.base.BaseFragment
import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.network.model.request.supplier.SupplierParamRequest
import com.arifwidayana.medstore.data.network.model.response.supplier.SupplierParamResponse
import com.arifwidayana.medstore.databinding.FragmentDetailSupplierBinding

class DetailSupplierFragment : BaseFragment<FragmentDetailSupplierBinding, DetailSupplierViewModel>(
    FragmentDetailSupplierBinding::inflate
) {
    private val args by navArgs<DetailSupplierFragmentArgs>()

    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        viewModel.getSupplier(args.id)
    }

    private fun onClick() {
        binding.apply {
            sivBack.setOnClickListener {
                moveNavigateUp()
            }
            btnUpdate.setOnClickListener {
                viewModel.updateSupplier(
                    idProduct = args.id,
                    supplierParamRequest = SupplierParamRequest(
                        supplierName = etSupplierName.text.toString(),
                        supplierAddress = etSupplierAddress.text.toString(),
                        supplierPhoneNumber = etSupplierPhoneNumber.text.toString()
                    )
                )
            }
            btnDelete.setOnClickListener {
                viewModel.deleteSupplier(args.id)
            }
        }
    }

    override fun observeData() {
        lifecycleScope.apply {
            launchWhenCreated {
                viewModel.getSupplierResult.collect {
                    when (it) {
                        is Resource.Empty -> {}
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            setStateProduct(it.data)
                        }
                        is Resource.Error -> {
                            showMessageSnackBar(true, exception = it.exception, message = it.message)
                        }
                    }
                }
            }
            launchWhenCreated {
                viewModel.updateSupplierResult.collect {
                    when (it) {
                        is Resource.Empty -> {}
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            showMessageSnackBar(true, it.message)
                            moveNavigateUp()
                        }
                        is Resource.Error -> {
                            showMessageSnackBar(true, exception = it.exception, message = it.message)
                        }
                    }
                }
            }
            launchWhenCreated {
                viewModel.deleteSupplierResult.collect {
                    when (it) {
                        is Resource.Empty -> {}
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            showMessageSnackBar(true, it.message)
                            moveNavigateUp()
                        }
                        is Resource.Error -> {
                            showMessageSnackBar(true, exception = it.exception, message = it.message)
                        }
                    }
                }
            }
        }
    }

    private fun setStateProduct(data: SupplierParamResponse?) {
        binding.apply {
            data?.let {
                etSupplierName.setText(it.supplierName)
                etSupplierAddress.setText(it.supplierAddress)
                etSupplierPhoneNumber.setText(it.supplierPhoneNumber)
            }
        }
    }
}