package com.example.soapshop.domain.events

import androidx.navigation.NavController

sealed interface ProfileEvents {

    data class OnUserExit(val navController: NavController): ProfileEvents

    data class OnFavouriteClick(val navController: NavController): ProfileEvents

    data class OnFavouritesItemClick(val itemId: String, val navController: NavController): ProfileEvents
}