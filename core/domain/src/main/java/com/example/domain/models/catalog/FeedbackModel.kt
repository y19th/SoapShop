package com.example.domain.models.catalog

import android.os.Parcelable
import androidx.compose.runtime.Stable
import com.example.data.models.FeedbackResponse
import com.example.data.room.entites.FeedbackEntity
import kotlinx.parcelize.Parcelize

@Stable
@Parcelize
data class FeedbackModel(
    val count: Int = 0,
    val rating: Float = 0f
) : Parcelable

fun FeedbackEntity.toFeedbackModel() = FeedbackModel(count, rating)

fun FeedbackResponse.toFeedbackModel() = FeedbackModel(count, rating)