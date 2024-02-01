package com.example.soapshop.domain.models.catalog

import androidx.compose.runtime.Stable

@Stable
data class PriceModel(
    val price: String = "",
    val discount: String = "",
    val priceWithDiscount: String = "",
    val unit: String = ""
)
