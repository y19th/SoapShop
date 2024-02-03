package com.example.domain.di

import com.example.data.repository.CatalogRepository
import com.example.domain.usecase.CatalogUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RetrofitUseCaseModule {
    @Provides
    fun provideCatalogUseCase(catalogRepository: CatalogRepository): CatalogUseCase {
        return CatalogUseCase(catalogRepository)
    }
}