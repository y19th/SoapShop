package com.example.soapshop.domain.events

import androidx.navigation.NavController

sealed interface ProfileEvents {

    data class OnUserExit(val navController: NavController): ProfileEvents
}