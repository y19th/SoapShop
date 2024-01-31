package com.example.soapshop.domain.states

import com.example.soapshop.domain.models.catalog.CatalogFilter

data class CatalogState(
    val filter: CatalogFilter = CatalogFilter.Popular
)
