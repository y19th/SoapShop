package com.example.soapshop.data.models

import com.example.soapshop.domain.models.catalog.PriceModel
import com.google.gson.annotations.SerializedName

data class PriceResponse(
    @SerializedName("price") val price: String = "",
    @SerializedName("discount") val discount: String = "",
    @SerializedName("priceWithDiscount") val priceWithDiscount: String = "",
    @SerializedName("unit") val unit: String = ""
) {
    fun toPriceModel() = PriceModel(price, discount, priceWithDiscount, unit)
}