package com.example.soapshop.presentation.components

import androidx.compose.foundation.border
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.soapshop.navigation.models.Destinations
import com.example.soapshop.navigation.models.Routes
import com.example.soapshop.ui.theme.BottomBarStroke
import com.example.soapshop.ui.theme.DarkGrey
import com.example.soapshop.ui.theme.MainTypography

@Composable
fun MainBottomBar(
    navController: NavController
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val destinationHierarchy = navBackStackEntry?.destination?.hierarchy

    if(destinationHierarchy?.any { it.route == Routes.REGISTRATION.name} != true) {
        BottomNavigation(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = BottomBarStroke
                ),
            backgroundColor = MaterialTheme.colorScheme.background,
            elevation = 2.dp
        ) {
            val destinations = rememberBottomBarDestinations()
            destinations.forEach { destination ->
                val isSelected = destinationHierarchy?.any {
                    it.route?.contains(destination.route.name) ?: false
                } == true

                BottomNavigationItem(
                    selected = isSelected,
                    onClick = {
                        navController.navigate(destination.route.name) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = destination.icon),
                            contentDescription = null,
                            tint = if(isSelected) MaterialTheme.colorScheme.primary else DarkGrey
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(id = destination.label),
                            style = MainTypography.caption,
                            color = if(isSelected) MaterialTheme.colorScheme.primary else DarkGrey
                        )
                    }
                )
            }
        }
    }

}


@Composable
fun rememberBottomBarDestinations(): List<Destinations> {
    return rememberSaveable {
        listOf(
            Destinations.MainDestination,
            Destinations.CatalogDestination,
            Destinations.CartDestination,
            Destinations.DiscountsDestination,
            Destinations.ProfileDestination
        )
    }
}