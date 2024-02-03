package com.example.data.room.entites

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.data.room.converters.InfoList
import com.example.data.room.converters.TagList

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false) val id: String = "",
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "subtitle") val subtitle: String = "",
    @ColumnInfo(name = "tags") val tags: TagList = TagList(),
    @ColumnInfo(name = "available") val available: Int = 0,
    @ColumnInfo(name = "description") val description: String = "",
    @ColumnInfo(name = "info") val info: InfoList = InfoList(),
    @ColumnInfo(name = "ingredients") val ingredients: String = ""
)

data class Product(
    @Embedded val productEntity: ProductEntity = ProductEntity(),
    @Relation(
        parentColumn = "id",
        entityColumn = "productOwnerId"
    ) val priceEntity: PriceEntity = PriceEntity(),
    @Relation(
        parentColumn = "id",
        entityColumn = "productOwnerId"
    ) val feedbackEntity: FeedbackEntity = FeedbackEntity()
)

