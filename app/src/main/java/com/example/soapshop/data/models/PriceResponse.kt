package com.example.soapshop.data.models

import com.google.gson.annotations.SerializedName

data class PriceResponse(
    @SerializedName("price") val price: String = "",
    @SerializedName("discount") val discount: String = "",
    @SerializedName("priceWithDiscount") val priceWithDiscount: String = "",
    @SerializedName("unit") val unit: String = ""
)