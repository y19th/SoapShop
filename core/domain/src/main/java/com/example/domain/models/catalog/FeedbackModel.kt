package com.example.domain.models.catalog

import androidx.compose.runtime.Stable
import com.example.data.models.FeedbackResponse
import com.example.data.room.entites.FeedbackEntity

@Stable
data class FeedbackModel(
    val count: Int = 0,
    val rating: Float = 0f
)

fun FeedbackEntity.toFeedbackModel() = FeedbackModel(count, rating)

fun FeedbackResponse.toFeedbackModel() = FeedbackModel(count, rating)