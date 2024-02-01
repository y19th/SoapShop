package com.example.soapshop.domain.models.catalog

import androidx.compose.runtime.Stable

@Stable
data class CatalogPinModel(
    val title: String = "",
    val tag: CatalogTag = CatalogTag.All,
) {
    companion object {
        val Deleted = CatalogPinModel(
            title = "DELETED",
            tag = CatalogTag.Deleted
        )
        val Default = CatalogPinModel(
            title = "Смотреть все",
            tag = CatalogTag.All
        )
    }
    override fun equals(other: Any?): Boolean {
        if(other !is CatalogPinModel) return false
        return other.title == this.title
    }

    override fun hashCode(): Int {
        return title.hashCode()
    }
}
