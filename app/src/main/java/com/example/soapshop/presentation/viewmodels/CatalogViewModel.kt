package com.example.soapshop.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.soapshop.domain.events.CatalogEvents
import com.example.soapshop.domain.models.catalog.CatalogPinModel
import com.example.soapshop.domain.states.CatalogState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(

): ViewModel(){


    private val _state = MutableStateFlow(CatalogState())
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(
                pinList = listOf(
                    CatalogPinModel(title = "Смотреть все"),
                    CatalogPinModel(title = "Лицо"),
                    CatalogPinModel(title = "Тело"),
                    CatalogPinModel(title = "Загар"),
                    CatalogPinModel(title = "Маски")
                )
            )
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
        }
    }

}