package com.arifwidayana.medstore.presentation.ui.product

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(

): ProductContract, ViewModel() {
}