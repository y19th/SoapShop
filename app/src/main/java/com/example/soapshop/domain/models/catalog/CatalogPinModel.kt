package com.example.soapshop.domain.models.catalog

data class CatalogPinModel(
    val title: String = ""
) {
    companion object {
        val Deleted = CatalogPinModel(title = "DELETED")
    }
    override fun equals(other: Any?): Boolean {
        if(other !is CatalogPinModel) return false
        return other.title == this.title
    }

    override fun hashCode(): Int {
        return title.hashCode()
    }
}
