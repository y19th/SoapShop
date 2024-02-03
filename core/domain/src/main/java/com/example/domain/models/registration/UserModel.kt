package com.example.domain.models.registration

import com.example.data.room.entites.UserEntity

data class UserModel(
    val id: Int = 0,
    val name: String = "",
    val surname: String = "",
    val phone: String = ""
)
fun UserEntity.toUserModel() = UserModel(
    id, name, surname, phone
)