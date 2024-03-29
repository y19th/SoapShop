package com.example.data.di

import android.content.Context
import com.example.data.repository.RoomRepository
import com.example.data.room.dao.ProductDao
import com.example.data.room.dao.UserDao
import com.example.data.room.schema.MainSchema
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideMainScheme(@ApplicationContext context: Context): MainSchema {
        return MainSchema.receiveInstance(context)
    }

    @Provides
    fun provideUserDao(mainSchema: MainSchema): UserDao {
        return mainSchema.userDao()
    }

    @Provides
    fun provideProductDao(mainSchema: MainSchema): ProductDao {
        return mainSchema.productDao()
    }

    @Provides
    fun provideRoomRepository(
        userDao: UserDao,
        productDao: ProductDao
    ): RoomRepository {
        return RoomRepository(userDao, productDao)
    }

}