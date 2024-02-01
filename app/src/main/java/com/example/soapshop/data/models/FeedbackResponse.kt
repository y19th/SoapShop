package com.example.soapshop.data.models

import com.example.soapshop.domain.models.catalog.FeedbackModel
import com.google.gson.annotations.SerializedName

data class FeedbackResponse(
    @SerializedName("count") val count: Int = 0,
    @SerializedName("rating") val rating: Float = 0f
) {
    fun toFeedbackModel() = FeedbackModel(count, rating)
}