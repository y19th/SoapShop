package com.example.domain.models.catalog

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.example.data.models.ProductResponse
import com.example.data.room.converters.InfoList
import com.example.data.room.converters.TagList
import com.example.data.room.entites.FeedbackEntity
import com.example.data.room.entites.PriceEntity
import com.example.data.room.entites.Product
import com.example.data.room.entites.ProductEntity
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
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
): Parcelable {
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

fun ProductResponse.toProductModel() = ProductModel(
    id = id,
    title = title,
    subtitle = subtitle,
    price = price.toPriceModel(),
    feedback = feedback.toFeedbackModel(),
    tags = tags,
    available = available,
    description = description,
    info = info.map { response -> response.toInfoModel() },
    ingredients = ingredients
)

fun Product.toProductModel() = ProductModel(
    id = productEntity.id,
    title = productEntity.title,
    subtitle = productEntity.subtitle,
    price = priceEntity.toPriceModel(),
    feedback = feedbackEntity.toFeedbackModel(),
    tags = productEntity.tags.tags,
    available = productEntity.available,
    description = productEntity.description,
    info = productEntity.info.list.map { entity -> entity.toInfoModel() },
    ingredients = productEntity.ingredients
)
