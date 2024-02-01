package com.example.soapshop.domain.usecase

import com.example.soapshop.data.repository.RoomRepository
import com.example.soapshop.data.room.entites.UserEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomUseCase @Inject constructor(
    private val repository: RoomRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun receiveUsers() = withContext(defaultDispatcher) {
        repository.receiveUsers()
    }

    suspend fun insertUsers(userEntity: UserEntity) = withContext(defaultDispatcher) {
        repository.insertUsers(userEntity)
    }
}