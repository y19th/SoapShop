package com.example.soapshop.domain.states

import com.example.soapshop.domain.models.registration.UserModel

data class ProfileState(
    val user: UserModel = UserModel(),
    val favourites: List<String> = listOf()
)
