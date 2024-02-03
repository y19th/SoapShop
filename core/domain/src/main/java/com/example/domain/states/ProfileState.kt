package com.example.domain.states

import com.example.domain.models.catalog.ProductModel
import com.example.domain.models.registration.UserModel

data class ProfileState(
    val user: UserModel = UserModel(),
    val favourites: List<ProductModel> = listOf()
)
