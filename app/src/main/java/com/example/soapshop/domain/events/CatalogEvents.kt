package com.example.soapshop.domain.events

import androidx.navigation.NavController
import com.example.soapshop.domain.models.catalog.CatalogFilter
import com.example.soapshop.domain.models.catalog.CatalogPinModel

sealed interface CatalogEvents {

    data object OnPinCancel: CatalogEvents
    data class OnFilterUpdate(val newValue: CatalogFilter): CatalogEvents

    data class OnPinSelected(val newValue: CatalogPinModel) : CatalogEvents

    data class OnItemClick(val itemId: String, val navController: NavController): CatalogEvents

}