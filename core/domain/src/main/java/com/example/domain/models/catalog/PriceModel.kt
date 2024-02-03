package com.example.domain.models.catalog

import androidx.compose.runtime.Stable
import com.example.data.models.PriceResponse
import com.example.data.room.entites.PriceEntity

@Stable
data class PriceModel(
    val price: String = "",
    val discount: String = "",
    val priceWithDiscount: String = "",
    val unit: String = ""
)

fun PriceEntity.toPriceModel() = PriceModel(
    price, discount, priceWithDiscount, unit
)

fun PriceResponse.toPriceModel() = PriceModel(
    price, discount, priceWithDiscount, unit
)
