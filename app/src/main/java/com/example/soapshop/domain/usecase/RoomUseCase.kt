package com.example.soapshop.domain.usecase

import com.example.soapshop.data.repository.RoomRepository
import com.example.soapshop.data.room.entites.FeedbackEntity
import com.example.soapshop.data.room.entites.PriceEntity
import com.example.soapshop.data.room.entites.ProductEntity
import com.example.soapshop.data.room.entites.UserEntity
import com.example.soapshop.domain.models.catalog.ProductModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomUseCase @Inject constructor(
    private val repository: RoomRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun receiveUsers() = withContext(defaultDispatcher) {
        repository.receiveUsers()
    }

    suspend fun insertUsers(userEntity: UserEntity) = withContext(defaultDispatcher) {
        repository.insertUsers(userEntity)
    }

    suspend fun eraseUsers() = withContext(defaultDispatcher) {
        repository.eraseUsers()
    }

    suspend fun receiveProductsId() = withContext(defaultDispatcher) {
        repository.receiveProductsId()
    }

    suspend fun receiveProducts() = withContext(defaultDispatcher) {
        repository.receiveProducts().map { entity -> entity.toProductModel() }
    }

    suspend fun deleteProduct(
        model: ProductModel
    ) = withContext(defaultDispatcher) {
        repository.deleteProduct(
            productEntity = model.toProductEntity(),
            priceEntity = model.toPriceEntity(),
            feedbackEntity = model.toFeedbackEntity()
        )
    }

    suspend fun insertProduct(
        model: ProductModel
    ) = withContext(defaultDispatcher) {
        repository.insertProduct(
            productEntity = model.toProductEntity(),
            priceEntity = model.toPriceEntity(),
            feedbackEntity = model.toFeedbackEntity()
        )
    }
}