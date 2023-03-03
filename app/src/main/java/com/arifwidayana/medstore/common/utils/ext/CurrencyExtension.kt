package com.arifwidayana.medstore.common.utils.ext

import java.text.DecimalFormat
import java.util.*

fun convertCurrency(value: Int): String {
    return DecimalFormat
        .getCurrencyInstance(Locale("id", "ID"))
        .format(value)
}
