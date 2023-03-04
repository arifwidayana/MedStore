package com.arifwidayana.medstore.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arifwidayana.medstore.data.network.model.response.supplier.SupplierParamResponse
import com.arifwidayana.medstore.databinding.ItemSupplierBinding

typealias SupplierDataset = SupplierParamResponse

class SupplierAdapter(
    private val onClick: (Int) -> Unit
) : PagingDataAdapter<SupplierDataset, SupplierAdapter.SupplierViewHolder>(DIFF_CALLBACK) {

    inner class SupplierViewHolder(
        private val binding: ItemSupplierBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SupplierDataset) {
            binding.apply {
                tvProductId.text = data.supplierId.toString()
                tvProductName.text = data.supplierName
                tvPhone.text = data.supplierPhoneNumber
                tvAddress.text = data.supplierAddress

                btnEdit.setOnClickListener {
                    onClick.invoke(data.supplierId)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: SupplierViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SupplierViewHolder {
        val binding = ItemSupplierBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SupplierViewHolder(binding)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SupplierDataset>() {
            override fun areItemsTheSame(oldItem: SupplierDataset, newItem: SupplierDataset): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: SupplierDataset, newItem: SupplierDataset): Boolean {
                return oldItem.supplierName == newItem.supplierName
            }
        }
    }
}