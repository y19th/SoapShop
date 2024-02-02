package com.example.soapshop.data.room.entites

import androidx.compose.runtime.Stable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.soapshop.domain.models.catalog.FeedbackModel

@Stable
@Entity(tableName = "feedback")
data class FeedbackEntity(
    @PrimaryKey(autoGenerate = true) val feedbackId: Int = 0,
    val count: Int = 0,
    val rating: Float = 0f,
    val productOwnerId: String = ""
) {
    fun toFeedbackModel() = FeedbackModel(
        count, rating
    )
}