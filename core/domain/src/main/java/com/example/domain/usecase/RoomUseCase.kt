package com.example.domain.usecase

import com.example.data.repository.RoomRepository
import com.example.data.room.entites.UserEntity
import com.example.domain.models.catalog.ProductModel
import com.example.domain.models.catalog.toProductModel
import com.example.domain.models.registration.toUserModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomUseCase @Inject constructor(
    private val repository: RoomRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun receiveUsers() = withContext(defaultDispatcher) {
        repository.receiveUsers().map { entity -> entity.toUserModel() }
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