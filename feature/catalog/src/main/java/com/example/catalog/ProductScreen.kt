package com.example.catalog


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ui.R
import com.example.catalog.components.ExpandedProductItem
import com.example.catalog.viewmodels.CatalogViewModel
import com.example.domain.events.CatalogEvents

@Composable
fun ProductScreen(
    viewModel: CatalogViewModel = hiltViewModel(),
    navController: NavController,
    productId: String
) {
    val state by viewModel.state.collectAsState()
    val isFavourite = state.favourites.contains(productId)

    val item by rememberSaveable(productId) {
        mutableStateOf(
            state.products.find { productModel ->
            productModel.id == productId
        } ?: state.products[0]
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .verticalScroll(rememberScrollState())
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 21.dp, end = 14.dp, bottom = 16.dp)
            ,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_catalog_arrow_back),
                contentDescription =  null,
                tint = com.example.ui.theme.Black,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .clip(CircleShape)
                    .clickable {
                        navController.navigateUp()
                    }
            )

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_catalog_send),
                contentDescription =  null,
                tint = com.example.ui.theme.Black
            )
        }

        ExpandedProductItem(
            item = item,
            isFavourite = isFavourite,
            onFavourite = {
                viewModel.onEvent(
                    CatalogEvents.OnFavourite(model = item)
                )
            }
        )

    }
}

