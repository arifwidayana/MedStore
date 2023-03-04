package com.arifwidayana.medstore.presentation.ui.product.detail

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.arifwidayana.medstore.common.base.BaseFragment
import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.data.network.model.request.product.ProductParamRequest
import com.arifwidayana.medstore.data.network.model.response.product.ProductParamResponse
import com.arifwidayana.medstore.databinding.FragmentDetailProductBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailProductFragment : BaseFragment<FragmentDetailProductBinding, DetailProductViewModel>(
    FragmentDetailProductBinding::inflate
) {
    private val args by navArgs<DetailProductFragmentArgs>()

    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        viewModel.getProduct(args.id)
    }

    private fun onClick() {
        binding.apply {
            sivBack.setOnClickListener {
                moveNavigateUp()
            }
            btnUpdate.setOnClickListener {
                viewModel.updateProduct(
                    idProduct = args.id,
                    productParamRequest = ProductParamRequest(
                        productName = etProductName.text.toString(),
                        price = etPrice.text.toString().toInt(),
                        stock = etStock.text.toString().toInt(),
                        supplierName = etSupplierName.text.toString()
                    )
                )
            }
            btnDelete.setOnClickListener {
                viewModel.deleteProduct(args.id)
            }
        }
    }

    override fun observeData() {
        lifecycleScope.apply {
            launchWhenCreated {
                viewModel.getProductResult.collect {
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
                viewModel.updateProductResult.collect {
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
                viewModel.deleteProductResult.collect {
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

    private fun setStateProduct(data: ProductParamResponse?) {
        binding.apply {
            data?.let {
                etProductId.setText(it.id.toString())
                etProductName.setText(it.productName)
                etPrice.setText(it.price.toString())
                etStock.setText(it.stock.toString())
                etSupplierName.setText(it.supplier.supplierName)
                etSupplierPhoneNumber.setText(it.supplier.phoneNumber)
                etSupplierAddress.setText(it.supplier.address)
            }
        }
    }
}