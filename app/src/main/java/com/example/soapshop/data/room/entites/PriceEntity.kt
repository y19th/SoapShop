package com.example.soapshop.data.room.entites

import androidx.compose.runtime.Stable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.soapshop.domain.models.catalog.PriceModel

@Stable
@Entity(tableName = "price")
data class PriceEntity(
    @PrimaryKey(autoGenerate = true) val priceId: Int = 0,
    val price: String = "",
    val discount: String = "",
    val priceWithDiscount: String = "",
    val unit: String = "",
    val productOwnerId: String = ""
) {
    fun toPriceModel() = PriceModel(
        price, discount, priceWithDiscount, unit
    )
}