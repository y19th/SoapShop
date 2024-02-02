package com.example.soapshop.di

import android.content.Context
import com.example.soapshop.data.repository.RoomRepository
import com.example.soapshop.domain.usecase.RoomUseCase
import com.example.soapshop.data.room.dao.UserDao
import com.example.soapshop.data.room.schema.MainSchema
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
    fun provideMainDao(mainSchema: MainSchema): UserDao {
        return mainSchema.mainDao()
    }

    @Provides
    fun provideRoomRepository(userDao: UserDao): RoomRepository {
        return RoomRepository(userDao)
    }

    @Provides
    fun provideRoomUseCase(roomRepository: RoomRepository): RoomUseCase {
        return RoomUseCase(roomRepository)
    }

}