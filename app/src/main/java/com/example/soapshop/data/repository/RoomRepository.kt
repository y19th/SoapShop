package com.example.soapshop.data.repository

import com.example.soapshop.domain.models.registration.UserModel
import com.example.soapshop.data.room.dao.UserDao
import com.example.soapshop.data.room.entites.UserEntity
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val userDao: UserDao
) {
    fun receiveUsers(): List<UserModel> {
        return userDao.receiveUsers().map { entity -> entity.toUserModel() }
    }

    fun insertUsers(vararg entities: UserEntity) {
        entities.forEach { userDao.insertUsers(it) }
    }

    fun eraseUsers() {
        userDao.eraseUsers()
    }
}