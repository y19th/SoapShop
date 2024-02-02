package com.example.soapshop.navigation.models

enum class Routes {
    MAIN,CATALOG,CART,DISCOUNTS,PROFILE,REGISTRATION;

    fun routeWith(string: String): String {
        return "${this.name}/$string"
    }
}