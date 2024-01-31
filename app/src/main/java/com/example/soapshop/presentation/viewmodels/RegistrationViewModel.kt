package com.example.soapshop.presentation.viewmodels

import android.telephony.PhoneNumberUtils
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soapshop.domain.events.RegistrationEvents
import com.example.soapshop.domain.states.RegistrationState
import com.example.soapshop.domain.usecase.RoomUseCase
import com.example.soapshop.navigation.models.Routes
import com.example.soapshop.room.entites.UserEntity
import com.example.soapshop.util.extension.isPhone
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val roomUseCase: RoomUseCase
): ViewModel() {
    companion object {
        const val TAG = "RegistrationViewModel"
    }


    private val _state = MutableStateFlow(RegistrationState())
    val state = _state.asStateFlow()

    /*TODO проверку на кириллицу*/
    /*TODO fix phone number */
    fun onEvent(event: RegistrationEvents) {
        when (event) {
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

            is RegistrationEvents.OnLogin -> {
                _state.update { it.copy(isLoading = true) }
                viewModelScope.launch {
                    with(state.value) {
                        roomUseCase.insertUsers(
                            userEntity = UserEntity(
                                id = 0,
                                name = name,
                                surname = surname,
                                phone = phone
                            )
                        )
                        event.navController.navigate(Routes.CATALOG.name)
                    }
                }
            }
            is RegistrationEvents.OnCheckAuth -> {
                viewModelScope.launch {
                    if (roomUseCase.receiveUsers().isNotEmpty()) {
                        event.navController.navigate(route = Routes.CATALOG.name)
                    }
                    else {
                        _state.update { it.copy(isLoading = false) }
                    }
                }
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

