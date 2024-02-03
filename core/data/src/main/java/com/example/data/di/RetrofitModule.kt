package com.example.data.di

import com.example.data.repository.CatalogRepository
import com.example.util.RetrofitBuilder
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
}