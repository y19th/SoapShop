package com.example.profile.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.events.ProfileEvents
import com.example.domain.models.nav.Routes
import com.example.domain.states.ProfileState
import com.example.domain.usecase.RoomUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
 private val roomUseCase: RoomUseCase
): ViewModel() {

    companion object {
        const val TAG = "ProfileViewModel"
    }

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    fun onEvent(event: ProfileEvents) {
        when(event) {
            is ProfileEvents.OnUserExit -> {
                viewModelScope.launch {
                    roomUseCase.eraseDatabase()
                }.invokeOnCompletion {
                    event.navController.navigate(Routes.REGISTRATION.name)

                }
            }
            is ProfileEvents.OnFavouriteClick -> {
                event.navController.navigate(
                    route = Routes.PROFILE.routeWith("favourites")
                )
            }
            is ProfileEvents.OnFavouritesItemClick -> {
                event.navController.navigate(
                    route = Routes.CATALOG.routeWith(event.itemId)
                )
            }
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    user = roomUseCase.receiveUsers().first(),
                    favourites = roomUseCase.receiveProducts()
                )
            }
        }
    }
}