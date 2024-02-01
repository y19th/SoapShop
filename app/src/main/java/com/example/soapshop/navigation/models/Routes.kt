package com.example.soapshop.navigation.models

enum class Routes {
    MAIN,CATALOG,CART,DISCOUNTS,PROFILE,REGISTRATION;

    fun routeWithItemId(itemId: String): String {
        return "${this.name}/$itemId"
    }
}