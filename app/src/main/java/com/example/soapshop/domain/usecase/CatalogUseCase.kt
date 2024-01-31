package com.example.soapshop.domain.usecase

import android.util.Log
import com.example.soapshop.data.models.CatalogResponse
import com.example.soapshop.data.repository.CatalogRepository
import com.example.soapshop.presentation.viewmodels.CatalogViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CatalogUseCase @Inject constructor(
    private val catalogRepository: CatalogRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun receiveProducts(): CatalogResponse? = withContext(defaultDispatcher) {
        return@withContext catalogRepository.receiveProducts().body().also {
            Log.d(CatalogViewModel.TAG, "response : $it")
        }
    }
}