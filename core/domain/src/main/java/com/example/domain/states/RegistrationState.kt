package com.example.domain.states

import androidx.compose.runtime.Stable

@Stable
data class RegistrationState(
    val name: String = "",
    val surname: String = "",
    val phone: String = "",

    val isLoading: Boolean = true,

    val isNameError: Boolean = false,
    val isSurnameError: Boolean = false,
    val isPhoneError: Boolean = false,

    val isValid: Boolean = false
)
