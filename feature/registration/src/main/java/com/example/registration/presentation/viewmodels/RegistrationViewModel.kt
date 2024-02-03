package com.example.registration.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.room.entites.UserEntity
import com.example.domain.events.RegistrationEvents
import com.example.domain.models.nav.Routes
import com.example.domain.states.RegistrationState
import com.example.domain.usecase.RoomUseCase
import com.example.util.extension.isNotCyrillic
import com.example.util.extension.isNotPhone
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

    fun onEvent(event: RegistrationEvents) {
        when (event) {
            is RegistrationEvents.OnNameChange -> {
                _state.update {
                    it.copy(
                        name = event.newValue,
                        isNameError = event.newValue.isNotCyrillic()
                    )
                }
                updateValid()
            }

            is RegistrationEvents.OnSurnameChange -> {
                _state.update {
                    it.copy(
                        surname = event.newValue,
                        isSurnameError = event.newValue.isNotCyrillic()
                    )
                }
                updateValid()
            }

            is RegistrationEvents.OnPhoneChange -> {
                _state.update {
                    it.copy(
                        phone = event.newValue,
                        isPhoneError = event.newValue.isNotPhone()
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

