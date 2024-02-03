package com.example.catalog.viewmodels

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ui.R
import com.example.domain.events.CatalogEvents
import com.example.domain.models.catalog.CatalogPinModel
import com.example.domain.models.catalog.CatalogTag
import com.example.domain.models.catalog.toProductModel
import com.example.domain.models.nav.Routes
import com.example.domain.states.CatalogState
import com.example.domain.usecase.CatalogUseCase
import com.example.domain.usecase.RoomUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    catalogUseCase: CatalogUseCase,
    private val roomUseCase: RoomUseCase
): ViewModel(){

    companion object {
        const val TAG = "CatalogViewModel"
    }

    private val _state = MutableStateFlow(CatalogState())
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(
                pinList = listOf(
                    CatalogPinModel.Default,
                    CatalogPinModel(
                        title = "Лицо",
                        tag = CatalogTag.Face
                    ),
                    CatalogPinModel(
                        title = "Тело",
                        tag = CatalogTag.Body
                    ),
                    CatalogPinModel(
                        title = "Загар",
                        tag = CatalogTag.Suntan
                    ),
                    CatalogPinModel(
                        title = "Маски",
                        tag = CatalogTag.Mask
                    )
                )
            )
        }
        viewModelScope.launch {
            val response = catalogUseCase.receiveProducts()
            _state.update {
                it.copy(
                    products = response.map { response -> response.toProductModel() }
                )
            }
        }
    }

    fun onEvent(event: CatalogEvents) {
        when(event) {
            is CatalogEvents.OnFilterUpdate -> {
                _state.update {
                    it.copy(filter = event.newValue)
                }
            }
            is CatalogEvents.OnPinSelected -> {
                _state.update {
                    it.copy(selectedPin = event.newValue)
                }
            }
            is CatalogEvents.OnPinCancel -> {
                _state.update {
                    it.copy(selectedPin = CatalogPinModel.Deleted)
                }
            }
            is CatalogEvents.OnItemClick -> {
                event.navController.navigate(
                    route = Routes.CATALOG.routeWith(event.itemId)
                )
            }
            is CatalogEvents.OnFavourite -> {
                if(state.value.favourites.contains(event.model.id)) {
                    viewModelScope.launch {
                        roomUseCase.deleteProduct(model = event.model)
                    }.invokeOnCompletion {
                        updateFavourites(
                            delete = true,
                            id = event.model.id
                        )
                    }
                } else {
                    viewModelScope.launch {
                        roomUseCase.insertProduct(model = event.model)
                    }.invokeOnCompletion {
                        updateFavourites(
                            delete = false,
                            id = event.model.id
                        )
                    }
                }
            }
        }
    }


    private fun updateFavourites(delete: Boolean, id: String) {
        _state.update {
            with(state.value.favourites) {
                it.copy(
                    favourites = if(delete) this.minus(id) else this.plus(id)
                )
            }
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    favourites = roomUseCase.receiveProductsId()
                )
            }
        }
    }
}
// hardcoded productId-image map
@Stable
object ProductMap {
    val map = mapOf<String,List<@DrawableRes Int>>(
        Pair(
            "cbf0c984-7c6c-4ada-82da-e29dc698bb50",
            listOf(R.drawable.ic_vox_blade,R.drawable.ic_eveline)
        ),
        Pair(
            "54a876a5-2205-48ba-9498-cfecff4baa6e",
            listOf(R.drawable.ic_deep_clean,R.drawable.ic_body_lotion)
        ),
        Pair(
            "75c84407-52e1-4cce-a73a-ff2d3ac031b3",
            listOf(R.drawable.ic_eveline,R.drawable.ic_vox_blade)
        ),
        Pair(
            "16f88865-ae74-4b7c-9d85-b68334bb97db",
            listOf(R.drawable.ic_beauty_deco,R.drawable.ic_hand_mask)
        ),
        Pair(
            "26f88856-ae74-4b7c-9d85-b68334bb97db",
            listOf(R.drawable.ic_body_lotion,R.drawable.ic_beauty_deco)
        ),
        Pair(
            "15f88865-ae74-4b7c-9d81-b78334bb97db",
            listOf(R.drawable.ic_vox_blade,R.drawable.ic_deep_clean)
        ),
        Pair(
            "88f88865-ae74-4b7c-9d81-b78334bb97db",
            listOf(R.drawable.ic_hand_mask,R.drawable.ic_beauty_deco)
        ),
        Pair(
            "55f58865-ae74-4b7c-9d81-b78334bb97db",
            listOf(R.drawable.ic_deep_clean,R.drawable.ic_eveline)
        )
    )
}