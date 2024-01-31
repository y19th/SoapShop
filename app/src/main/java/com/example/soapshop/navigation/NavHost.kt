package com.example.soapshop.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.soapshop.navigation.models.Routes
import com.example.soapshop.presentation.screens.CatalogScreen
import com.example.soapshop.presentation.screens.RegistrationScreen

@Composable
fun NavHostContainer(
    navHostController: NavHostController,
    paddingValues: PaddingValues
) {

    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navHostController,
        startDestination = Routes.REGISTRATION.name,
        builder = {
            composable(route = Routes.MAIN.name) {

            }
            composable(route = Routes.CATALOG.name) {
                CatalogScreen()
            }
            composable(route = Routes.CART.name) {

            }
            composable(route = Routes.DISCOUNTS.name) {

            }
            composable(route = Routes.PROFILE.name) {

            }
            composable(route = Routes.REGISTRATION.name) {
                RegistrationScreen(navController = navHostController)
            }
        }
    )
}