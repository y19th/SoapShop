package com.example.soapshop.presentation.viewmodels

import android.telephony.PhoneNumberUtils
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

/*TODO проверку на кириллицу*/
    fun onEvent(event: RegistrationEvents) {
        when(event) {
            is RegistrationEvents.OnNameChange -> {
                _state.update {
                    it.copy(
                        name = event.newValue,
                        isNameError = event.newValue.isEmpty()
                    )
                }
                updateValid()
            }
            is RegistrationEvents.OnSurnameChange -> {
                _state.update {
                    it.copy(
                        surname = event.newValue,
                        isSurnameError = event.newValue.isEmpty()
                    )
                }
                updateValid()
            }
            is RegistrationEvents.OnPhoneChange -> {
                _state.update {
                    it.copy(
                        phone = event.newValue,
                        isPhoneError = event.newValue.isPhone().not()
                    )
                }
                updateValid()
            }
        }
    }

    private fun updateValid() {
        _state.update { it.copy(isValid = isFieldsFilled()) }
    }

    private fun isFieldsFilled(): Boolean {
        with(state.value) {
            return (name.isNotEmpty() && surname.isNotEmpty() && phone.isNotEmpty()) &&
                    !(isNameError || isSurnameError || isPhoneError)
        }
    }
}

fun String.isPhone(): Boolean {
    return PhoneNumberUtils.isGlobalPhoneNumber(this) && this.length == 10
}