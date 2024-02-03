package com.example.data.repository

import com.example.data.api.CatalogApi
import com.example.util.RetrofitBuilder
import javax.inject.Inject

class CatalogRepository @Inject constructor(
    retrofitBuilder: RetrofitBuilder
) {
    val instance = retrofitBuilder.instance(CatalogApi::class.java)

    suspend fun receiveProducts() = instance.receiveGoods()
}