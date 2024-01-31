package com.example.soapshop.domain.events

import androidx.navigation.NavController

sealed interface RegistrationEvents {

    data object OnLogin: RegistrationEvents

    data class OnCheckAuth(val navController: NavController): RegistrationEvents

    data class OnNameChange(val newValue: String) : RegistrationEvents

    data class OnSurnameChange(val newValue: String) : RegistrationEvents

    data class OnPhoneChange(val newValue: String) : RegistrationEvents
}