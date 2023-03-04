package com.arifwidayana.medstore.presentation.ui.product

import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.arifwidayana.medstore.R
import com.arifwidayana.medstore.common.base.BaseFragment
import com.arifwidayana.medstore.common.wrapper.Resource
import com.arifwidayana.medstore.databinding.FragmentProductBinding
import com.arifwidayana.medstore.presentation.adapter.ProductAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment : BaseFragment<FragmentProductBinding, ProductViewModel>(
    FragmentProductBinding::inflate
) {
    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        viewModel.getProduct()
    }

    private fun onClick() {
        binding.apply {
            fabAddProduct.setOnClickListener {
                moveNav(R.id.action_productFragment_to_addProductFragment)
            }
        }
    }

    override fun observeData() {
        lifecycleScope.launchWhenCreated {
            viewModel.getProductResult.asLiveData().observe(viewLifecycleOwner) {
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
    }

    private fun setStateProduct(data: ProductDataResult?) {
        binding.apply {
            data?.let {
                val adapter = ProductAdapter { res ->
                    moveNav(
                        ProductFragmentDirections.actionProductFragmentToDetailProductFragment()
                            .setId(res)
                    )
                }
                adapter.submitData(lifecycle, it)
                rvProduct.adapter = adapter
            }
        }
    }
}