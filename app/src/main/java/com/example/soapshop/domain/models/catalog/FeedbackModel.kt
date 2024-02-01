package com.example.soapshop.domain.models.catalog

import androidx.compose.runtime.Stable


@Stable
data class FeedbackModel(
    val count: Int = 0,
    val rating: Float = 0f
)