package com.example.domain.models.catalog

import android.os.Parcelable
import androidx.compose.runtime.Stable
import com.example.data.models.PriceResponse
import com.example.data.room.entites.PriceEntity
import kotlinx.parcelize.Parcelize

@Stable
@Parcelize
data class PriceModel(
    val price: String = "",
    val discount: String = "",
    val priceWithDiscount: String = "",
    val unit: String = ""
) : Parcelable

fun PriceEntity.toPriceModel() = PriceModel(
    price, discount, priceWithDiscount, unit
)

fun PriceResponse.toPriceModel() = PriceModel(
    price, discount, priceWithDiscount, unit
)
