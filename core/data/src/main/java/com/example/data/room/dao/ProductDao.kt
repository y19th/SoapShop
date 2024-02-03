package com.example.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.room.entites.FeedbackEntity
import com.example.data.room.entites.PriceEntity
import com.example.data.room.entites.Product
import com.example.data.room.entites.ProductEntity

@Dao
interface ProductDao {

    @Insert
    fun insertProduct(productEntity: ProductEntity)

    @Insert
    fun insertPrice(priceEntity: PriceEntity)

    @Insert
    fun insertFeedBack(feedbackEntity: FeedbackEntity)

    @Delete
    fun deleteProduct(productEntity: ProductEntity)

    @Delete
    fun deletePrice(priceEntity: PriceEntity)

    @Delete
    fun deleteFeedback(feedbackEntity: FeedbackEntity)

    @Query("delete from products")
    fun eraseProducts()

    @Query("delete from price")
    fun erasePrices()

    @Query("delete from feedback")
    fun eraseFeedbacks()


    @Query("select id from products")
    fun receiveProductsId(): List<String>

    @Transaction
    @Query("select * from products")
    fun receiveProducts(): List<Product>
}