package com.example.soapshop.data.models

import com.example.soapshop.domain.models.catalog.ProductModel
import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("id") val id: String = "",
    @SerializedName("title") val title: String = "",
    @SerializedName("subtitle") val subtitle: String = "",
    @SerializedName("price") val price: PriceResponse = PriceResponse(),
    @SerializedName("feedback") val feedback: FeedbackResponse = FeedbackResponse(),
    @SerializedName("tags") val tags: List<String> = listOf(),
    @SerializedName("available") val available: Int = 0,
    @SerializedName("description") val description: String = "",
    @SerializedName("info") val info: List<InfoResponse> = listOf(),
    @SerializedName("ingredients") val ingredients: String = ""
) {
    fun toProductModel() = ProductModel(
        id = id,
        title = title,
        subtitle = subtitle,
        price = price.toPriceModel(),
        feedback = feedback.toFeedbackModel(),
        tags = tags,
        available = available,
        description = description,
        info = info.map { response -> response.toInfoModel() },
        ingredients = ingredients
    )
}