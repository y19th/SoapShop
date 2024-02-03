package com.example.domain.states

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.example.domain.models.catalog.CatalogFilter
import com.example.domain.models.catalog.CatalogPinModel
import com.example.domain.models.catalog.ProductModel

@Stable
data class CatalogState(
    val filter: CatalogFilter = CatalogFilter.Popular,

    val pinList: List<CatalogPinModel> = listOf(),
    val selectedPin: CatalogPinModel = CatalogPinModel.Default,

    val products: List<ProductModel> = listOf(),

    val favourites: List<String> = listOf()
)
