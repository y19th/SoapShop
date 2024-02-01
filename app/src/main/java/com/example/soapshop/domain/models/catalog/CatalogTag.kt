package com.example.soapshop.domain.models.catalog

import androidx.compose.runtime.Stable

@Stable
sealed class CatalogTag(val tag: String) {

    data object All: CatalogTag("all")

    data object Face: CatalogTag("face")

    data object Body: CatalogTag("body")

    data object Suntan: CatalogTag("suntan")

    data object Mask: CatalogTag("mask")

    data object Deleted: CatalogTag("DELETED")
}