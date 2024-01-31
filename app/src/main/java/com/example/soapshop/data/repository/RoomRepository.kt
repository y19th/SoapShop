package com.example.soapshop.data.repository

import com.example.soapshop.domain.models.UserModel
import com.example.soapshop.room.dao.MainDao
import com.example.soapshop.room.entites.UserEntity
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val mainDao: MainDao
) {
    fun receiveUsers(): List<UserModel> {
        return mainDao.receiveUsers().map { entity -> entity.toUserModel() }
    }

    fun insertUsers(vararg entities: UserEntity) {
        entities.forEach { mainDao.insertUsers(it) }
    }

}