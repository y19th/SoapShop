package com.example.domain.events

import androidx.navigation.NavController
import com.example.domain.models.catalog.CatalogFilter
import com.example.domain.models.catalog.CatalogPinModel
import com.example.domain.models.catalog.ProductModel

sealed interface CatalogEvents {

    data object OnPinCancel: CatalogEvents
    data class OnFilterUpdate(val newValue: CatalogFilter): CatalogEvents

    data class OnPinSelected(val newValue: CatalogPinModel) : CatalogEvents

    data class OnItemClick(val itemId: String, val navController: NavController): CatalogEvents

    data class OnFavourite(val model: ProductModel): CatalogEvents

}