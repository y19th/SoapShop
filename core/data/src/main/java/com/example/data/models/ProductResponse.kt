package com.example.data.models

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
)