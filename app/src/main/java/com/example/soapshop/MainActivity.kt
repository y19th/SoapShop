package com.example.soapshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.soapshop.navigation.NavHostContainer
import com.example.soapshop.ui.theme.SoapShopTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoapShopTheme { paddingValues, navHostController ->
                NavHostContainer(
                    navHostController = navHostController,
                    paddingValues = paddingValues
                )
            }
        }
    }
}