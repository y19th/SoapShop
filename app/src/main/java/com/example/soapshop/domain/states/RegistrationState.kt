package com.example.soapshop.domain.states

import androidx.compose.runtime.Stable

@Stable
data class RegistrationState(
    val name: String = "",
    val surname: String = "",
    val phone: String = ""
)
