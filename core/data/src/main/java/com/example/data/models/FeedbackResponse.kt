package com.example.data.models

import com.google.gson.annotations.SerializedName

data class FeedbackResponse(
    @SerializedName("count") val count: Int = 0,
    @SerializedName("rating") val rating: Float = 0f
)