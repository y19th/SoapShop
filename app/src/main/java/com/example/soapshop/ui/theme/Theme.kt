package com.example.soapshop.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.soapshop.presentation.components.MainBottomBar

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
    onError = Orange

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