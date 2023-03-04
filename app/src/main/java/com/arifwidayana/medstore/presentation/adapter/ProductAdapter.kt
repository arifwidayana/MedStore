package com.arifwidayana.medstore.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arifwidayana.medstore.data.network.model.response.product.ProductParamResponse
import com.arifwidayana.medstore.databinding.ItemProductBinding

typealias ProductDataset = ProductParamResponse

class ProductAdapter(
    private val onClick: (Int) -> Unit
) : PagingDataAdapter<ProductDataset, ProductAdapter.ProductViewHolder>(DIFF_CALLBACK) {

    inner class ProductViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ProductDataset) {
            binding.apply {
                tvProductId.text = data.id.toString()
                tvProductName.text = data.productName
                tvPrice.text = data.price.toString()
                tvStock.text = data.stock.toString()
                tvSupplierName.text = data.supplier.supplierName
                tvPhoneNumber.text = data.supplier.phoneNumber
                tvAddress.text = data.supplier.address

                btnEdit.setOnClickListener {
                    onClick.invoke(data.id)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductDataset>() {
            override fun areItemsTheSame(oldItem: ProductDataset, newItem: ProductDataset): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ProductDataset, newItem: ProductDataset): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}