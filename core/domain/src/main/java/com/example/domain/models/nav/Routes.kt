package com.example.domain.models.nav

enum class Routes {
    MAIN,CATALOG,CART,DISCOUNTS,PROFILE,REGISTRATION;

    fun routeWith(string: String): String {
        return "${this.name}/$string"
    }
}