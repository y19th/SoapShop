package com.example.soapshop.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.soapshop.domain.events.RegistrationEvents
import com.example.soapshop.domain.states.RegistrationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(): ViewModel() {
    companion object {
        const val TAG = "RegistrationViewModel"
    }


    private val _state = MutableStateFlow(RegistrationState())
    val state = _state.asStateFlow()


    fun onEvent(event: RegistrationEvents) {
        when(event) {
            is RegistrationEvents.OnNameChange -> {
                _state.update { it.copy(name = event.newValue) }
            }
            is RegistrationEvents.OnSurnameChange -> {
                _state.update { it.copy(surname = event.newValue) }
            }
            is RegistrationEvents.OnPhoneChange -> {
                _state.update { it.copy(phone = event.newValue) }
            }
        }
    }

}