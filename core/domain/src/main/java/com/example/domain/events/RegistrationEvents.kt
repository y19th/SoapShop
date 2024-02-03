package com.example.domain.events

import androidx.navigation.NavController

sealed interface RegistrationEvents {

    data class OnLogin(val navController: NavController): RegistrationEvents

    data class OnCheckAuth(val navController: NavController): RegistrationEvents

    data class OnNameChange(val newValue: String) : RegistrationEvents

    data class OnSurnameChange(val newValue: String) : RegistrationEvents

    data class OnPhoneChange(val newValue: String) : RegistrationEvents
}