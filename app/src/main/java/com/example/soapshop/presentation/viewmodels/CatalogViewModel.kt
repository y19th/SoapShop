package com.example.soapshop.presentation.viewmodels

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soapshop.R
import com.example.soapshop.domain.events.CatalogEvents
import com.example.soapshop.domain.models.catalog.CatalogPinModel
import com.example.soapshop.domain.models.catalog.CatalogTag
import com.example.soapshop.domain.states.CatalogState
import com.example.soapshop.domain.usecase.CatalogUseCase
import com.example.soapshop.navigation.models.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    catalogUseCase: CatalogUseCase
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
                    CatalogPinModel(title = "Лицо", tag = CatalogTag.Face),
                    CatalogPinModel(title = "Тело", tag = CatalogTag.Body),
                    CatalogPinModel(title = "Загар", tag = CatalogTag.Suntan),
                    CatalogPinModel(title = "Маски", tag = CatalogTag.Mask)
                )
            )
        }
        viewModelScope.launch {
            val response = catalogUseCase.receiveProducts()
            if(response != null) {
                _state.update {
                    it.copy(
                        products = response.items.map { response -> response.toProductModel() }
                    )
                }
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
                    route = Routes.CATALOG.routeWithItemId(event.itemId)
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