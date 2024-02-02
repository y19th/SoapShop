package com.example.soapshop.domain.models.catalog

import androidx.compose.runtime.Immutable
import com.example.soapshop.data.room.converters.InfoList
import com.example.soapshop.data.room.converters.TagList
import com.example.soapshop.data.room.entites.FeedbackEntity
import com.example.soapshop.data.room.entites.PriceEntity
import com.example.soapshop.data.room.entites.ProductEntity

@Immutable
data class ProductModel(
    val id: String = "",
    val title: String = "",
    val subtitle: String = "",
    val price: PriceModel = PriceModel(),
    val feedback: FeedbackModel = FeedbackModel(),
    val tags: List<String> = listOf(),
    val available: Int = 0,
    val description: String = "",
    val info: List<InfoModel> = listOf(),
    val ingredients: String = ""
) {
    fun toProductEntity() = ProductEntity(
        id = id,
        title = title,
        subtitle = subtitle,
        tags = TagList(tags = tags),
        available = available,
        description = description,
        info = InfoList(list = info.map { model -> model.toInfoEntity() }),
        ingredients = ingredients
    )
    fun toPriceEntity() = PriceEntity(
        price = price.price,
        discount = price.discount,
        priceWithDiscount = price.priceWithDiscount,
        unit = price.unit
    )

    fun toFeedbackEntity() = FeedbackEntity(
        count = feedback.count,
        rating = feedback.rating
    )
}
