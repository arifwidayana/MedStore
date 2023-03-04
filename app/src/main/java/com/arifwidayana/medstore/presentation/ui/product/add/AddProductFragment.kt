package com.arifwidayana.medstore.presentation.ui.product.add

import androidx.lifecycle.lifecycleScope
import com.arifwidayana.medstore.common.base.BaseFragment
import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.network.model.request.product.add.AddProductParamRequest
import com.arifwidayana.medstore.databinding.FragmentAddProductBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddProductFragment : BaseFragment<FragmentAddProductBinding, AddProductViewModel>(
    FragmentAddProductBinding::inflate
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
                viewModel.addProduct(
                    addProductParamRequest = AddProductParamRequest(
                        productName = etProductName.text.toString(),
                        price = etPrice.text.toString().toInt(),
                        stock = etStock.text.toString().toInt(),
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
            viewModel.addProductResult.collect {
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