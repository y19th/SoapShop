package com.example.data.repository

import com.example.data.room.dao.ProductDao
import com.example.data.room.dao.UserDao
import com.example.data.room.entites.FeedbackEntity
import com.example.data.room.entites.PriceEntity
import com.example.data.room.entites.Product
import com.example.data.room.entites.ProductEntity
import com.example.data.room.entites.UserEntity
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val userDao: UserDao,
    private val productDao: ProductDao
) {
    fun receiveUsers(): List<UserEntity> {
        return userDao.receiveUsers()
    }

    fun insertUsers(vararg entities: UserEntity) {
        entities.forEach { userDao.insertUsers(it) }
    }

    fun eraseUsers() {
        userDao.eraseUsers()
    }

    fun receiveProductsId(): List<String> {
        return productDao.receiveProductsId()
    }

    fun receiveProducts() : List<Product> {
        return productDao.receiveProducts()
    }

    fun eraseProducts() {
        productDao.apply {
            eraseProducts()
            eraseFeedbacks()
            erasePrices()
        }
    }

    fun deleteProduct(
        productEntity: ProductEntity,
        priceEntity: PriceEntity,
        feedbackEntity: FeedbackEntity
    ) {
        productDao.apply {
            deleteProduct(productEntity)
            deletePrice(priceEntity.copy(productOwnerId = productEntity.id))
            deleteFeedback(feedbackEntity.copy(productOwnerId = productEntity.id))
        }
    }

    fun insertProduct(
        productEntity: ProductEntity,
        priceEntity: PriceEntity,
        feedbackEntity: FeedbackEntity
    ) {
        productDao.apply {
            insertProduct(productEntity)
            insertPrice(priceEntity.copy(productOwnerId = productEntity.id))
            insertFeedBack(feedbackEntity.copy(productOwnerId = productEntity.id))
        }
    }
}