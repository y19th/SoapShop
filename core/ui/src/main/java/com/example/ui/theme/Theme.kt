package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.domain.models.nav.Destinations
import com.example.domain.models.nav.Routes


private val DarkColorScheme = darkColorScheme(
    background = White,

    surface = White,
    onSurface = Black,
    surfaceVariant = LightGrey,
    onSurfaceVariant = Black,

    primary = Pink,
    onPrimary = White,
    secondary = LightPink,
    onSecondary = White
)

private val LightColorScheme = lightColorScheme(

    background = White,

    surface = White,
    onSurface = Black,
    surfaceVariant = LightGrey,
    onSurfaceVariant = Black,

    primary = Pink,
    onPrimary = White,
    secondary = LightPink,
    onSecondary = White,

    error = Orange,
    onError = Orange,

    outline = LightGrey

)

@Composable
fun SoapShopTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable (PaddingValues, NavHostController) -> Unit
) {

    val navController = rememberNavController()

    val colorScheme = when {
      /*dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }*/
      darkTheme -> {
          DarkColorScheme
      }
      else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
      SideEffect {
        val window = (view.context as Activity).window
        window.statusBarColor = colorScheme.primary.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
      }
    }
    MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            bottomBar = {
                MainBottomBar(navController = navController)
            },
            content = { paddingValues ->
                content.invoke(paddingValues,navController)
            }
        )
    }
}


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


var disabledState by mutableStateOf(LightGrey, structuralEqualityPolicy())
    private set
var onDisabledState by mutableStateOf(Grey, structuralEqualityPolicy())
    private set

@Suppress("UnusedReceiverParameter")
var ColorScheme.disabled: Color
    get() = disabledState
    private set(value) {
        disabledState = value
    }
@Suppress("UnusedReceiverParameter")
var ColorScheme.onDisabled: Color
    get() = onDisabledState
    private set(value) {
        onDisabledState = value
    }