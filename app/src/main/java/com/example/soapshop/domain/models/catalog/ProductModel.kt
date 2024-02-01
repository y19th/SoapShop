package com.example.soapshop.domain.models.catalog

import androidx.compose.runtime.Stable

@Stable
data class ProductModel(
    val id: String = "",
    val title: String = "",
    val subtitle: String = "",
    val price: PriceModel = PriceModel(),
    val feedback: FeedbackModel = FeedbackModel(),
    val tags: List<String> = listOf(),
    val available: Int = 0,
    val description: String = "",
    val info: List<InfoModel> = listOf(),
    val ingredients: String = ""
)
