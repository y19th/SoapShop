package com.example.soapshop

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.domain.models.nav.Routes
import com.example.catalog.CatalogScreen
import com.example.catalog.ProductScreen
import com.example.main.MainScreen
import com.example.profile.ProfileScreen
import com.example.registration.presentation.RegistrationScreen
import com.example.catalog.viewmodels.CatalogViewModel
import com.example.profile.FavouritesScreen
import com.example.profile.viewmodels.ProfileViewModel

@Composable
fun NavHostContainer(
    navHostController: NavHostController,
    paddingValues: PaddingValues
) {

    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No viewModelStoreOwner provided"
    }

    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navHostController,
        startDestination = Routes.REGISTRATION.name,
        builder = {
            composable(route = Routes.MAIN.name) {
                MainScreen()
            }
            composable(route = Routes.CATALOG.name) {
                CatalogScreen(
                    viewModel = hiltViewModel(
                        viewModelStoreOwner = viewModelStoreOwner,
                        key = CatalogViewModel.TAG
                    ),
                    navController = navHostController
                )
            }
            composable(route = Routes.CART.name) {

            }
            composable(route = Routes.DISCOUNTS.name) {

            }
            composable(route = Routes.PROFILE.name) {
                ProfileScreen(
                    viewModel = hiltViewModel(
                        viewModelStoreOwner = viewModelStoreOwner,
                        key = ProfileViewModel.TAG
                    ),
                    navController = navHostController
                )
            }
            composable(route = Routes.REGISTRATION.name) {

                BackHandler(true) {}
                RegistrationScreen(navController = navHostController)
            }
            composable(
                route = Routes.CATALOG.routeWith(string = "{productId}"),
                arguments = listOf(navArgument("productId") { type = NavType.StringType })
            ) { navBackStackEntry ->
                ProductScreen(
                    viewModel = hiltViewModel(
                        viewModelStoreOwner = viewModelStoreOwner,
                        key = CatalogViewModel.TAG
                    ),
                    navController = navHostController,
                    productId = navBackStackEntry.arguments?.getString("productId") ?: ""
                )
            }

            composable(
                route = Routes.PROFILE.routeWith(string = "favourites")
            ) {
                FavouritesScreen(
                    viewModel = hiltViewModel(
                        viewModelStoreOwner = viewModelStoreOwner,
                        key = ProfileViewModel.TAG
                    ),
                    navController = navHostController
                )
            }
        }
    )
}