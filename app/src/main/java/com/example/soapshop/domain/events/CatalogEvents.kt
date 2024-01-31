package com.example.soapshop.domain.events

import com.example.soapshop.domain.models.catalog.CatalogFilter

sealed interface CatalogEvents {

    data class OnFilterUpdate(val newValue: CatalogFilter): CatalogEvents
}