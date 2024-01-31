package com.example.soapshop.domain.states

import com.example.soapshop.domain.models.catalog.CatalogFilter
import com.example.soapshop.domain.models.catalog.CatalogPinModel

data class CatalogState(
    val filter: CatalogFilter = CatalogFilter.Popular,

    val pinList: List<CatalogPinModel> = listOf(),
    val selectedPin: CatalogPinModel = CatalogPinModel("a")
)
