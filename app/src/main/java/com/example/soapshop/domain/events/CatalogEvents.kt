package com.example.soapshop.domain.events

import com.example.soapshop.domain.models.catalog.CatalogFilter
import com.example.soapshop.domain.models.catalog.CatalogPinModel

sealed interface CatalogEvents {

    data object OnPinCancel: CatalogEvents
    data class OnFilterUpdate(val newValue: CatalogFilter): CatalogEvents

    data class OnPinSelected(val newValue: CatalogPinModel) : CatalogEvents

}