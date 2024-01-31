package com.example.soapshop.data.repository

import com.example.soapshop.data.api.CatalogApi
import com.example.soapshop.util.RetrofitBuilder
import javax.inject.Inject

class CatalogRepository @Inject constructor(
    retrofitBuilder: RetrofitBuilder
) {
    val instance = retrofitBuilder.instance(CatalogApi::class.java)

    suspend fun receiveProducts() = instance.receiveGoods()
}