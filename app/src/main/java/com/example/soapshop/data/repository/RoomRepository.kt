package com.example.soapshop.data.repository

import com.example.soapshop.data.room.dao.ProductDao
import com.example.soapshop.domain.models.registration.UserModel
import com.example.soapshop.data.room.dao.UserDao
import com.example.soapshop.data.room.entites.FeedbackEntity
import com.example.soapshop.data.room.entites.PriceEntity
import com.example.soapshop.data.room.entites.Product
import com.example.soapshop.data.room.entites.ProductEntity
import com.example.soapshop.data.room.entites.UserEntity
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val userDao: UserDao,
    private val productDao: ProductDao
) {
    fun receiveUsers(): List<UserModel> {
        return userDao.receiveUsers().map { entity -> entity.toUserModel() }
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