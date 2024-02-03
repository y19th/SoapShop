package com.example.data.room.entites

import androidx.compose.runtime.Stable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Stable
@Entity(tableName = "price")
data class PriceEntity(
    @PrimaryKey(autoGenerate = true) val priceId: Int = 0,
    val price: String = "",
    val discount: String = "",
    val priceWithDiscount: String = "",
    val unit: String = "",
    val productOwnerId: String = ""
)