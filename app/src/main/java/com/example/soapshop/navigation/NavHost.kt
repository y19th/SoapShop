package com.example.soapshop.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.soapshop.navigation.models.Routes
import com.example.soapshop.presentation.screens.catalog.CatalogScreen
import com.example.soapshop.presentation.screens.main.MainScreen
import com.example.soapshop.presentation.screens.registration.RegistrationScreen

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
                MainScreen()
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