package com.example.soapshop.presentation.screens.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.soapshop.R
import com.example.soapshop.presentation.viewmodels.CatalogViewModel
import com.example.soapshop.ui.theme.Black

@Composable
fun ProductScreen(
    viewModel: CatalogViewModel = hiltViewModel(),
    navController: NavController,
    productId: String
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_catalog_arrow_back),
                contentDescription =  null,
                tint = Black
            )

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_catalog_send),
                contentDescription =  null,
                tint = Black
            )
        }
    }
}