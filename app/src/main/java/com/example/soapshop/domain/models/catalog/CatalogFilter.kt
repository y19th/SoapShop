package com.example.soapshop.domain.models.catalog

import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import com.example.soapshop.R

@Stable
sealed class CatalogFilter(@StringRes val title: Int) {
    data object Popular: CatalogFilter(R.string.filter_popular)

    data object PriceUp: CatalogFilter(R.string.filter_priceRise)

    data object PriceDown: CatalogFilter(R.string.filter_priceDown)
}
