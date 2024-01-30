package com.example.soapshop.domain.events

sealed interface RegistrationEvents {

    data class OnNameChange(val newValue: String) : RegistrationEvents

    data class OnSurnameChange(val newValue: String) : RegistrationEvents

    data class OnPhoneChange(val newValue: String) : RegistrationEvents
}