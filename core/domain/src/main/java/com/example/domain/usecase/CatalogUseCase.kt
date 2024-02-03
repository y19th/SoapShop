package com.example.domain.usecase

import com.example.data.repository.CatalogRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CatalogUseCase @Inject constructor(
    private val catalogRepository: CatalogRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun receiveProducts() = withContext(defaultDispatcher) {
       val body = catalogRepository.receiveProducts().body()
        if(body != null) {
            return@withContext body.items
        }
        return@withContext listOf()
    }
}