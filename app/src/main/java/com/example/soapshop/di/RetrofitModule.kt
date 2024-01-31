package com.example.soapshop.di

import com.example.soapshop.data.repository.CatalogRepository
import com.example.soapshop.domain.usecase.CatalogUseCase
import com.example.soapshop.util.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideRetrofitBuilder() : RetrofitBuilder {
        return RetrofitBuilder()
    }

    @Provides
    fun provideCatalogRepository(retrofitBuilder: RetrofitBuilder) : CatalogRepository {
        return CatalogRepository(retrofitBuilder)
    }

    @Provides
    fun provideCatalogUseCase(catalogRepository: CatalogRepository): CatalogUseCase {
        return CatalogUseCase(catalogRepository)
    }
}