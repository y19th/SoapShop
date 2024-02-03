package com.example.domain.models.nav

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.domain.R

sealed class Destinations(@StringRes val label: Int,@DrawableRes val icon: Int, val route: Routes) {
    data object MainDestination: Destinations(
        label = R.string.bar_main,
        icon = R.drawable.ic_bottom_bar_main,
        route = Routes.MAIN
    )
    data object CatalogDestination: Destinations(
        label = R.string.bar_catalog,
        icon = R.drawable.ic_bottom_bar_catalog,
        route = Routes.CATALOG
    )
    data object CartDestination: Destinations(
        label = R.string.bar_cart,
        icon = R.drawable.ic_bottom_bar_cart,
        route = Routes.CART
    )

    data object DiscountsDestination: Destinations(
        label = R.string.bar_discounts,
        icon = R.drawable.ic_bottom_bar_discounts,
        route = Routes.DISCOUNTS
    )

    data object ProfileDestination: Destinations(
        label = R.string.bar_profile,
        icon = R.drawable.ic_bottom_bar_profile,
        route = Routes.PROFILE
    )
}