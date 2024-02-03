package com.example.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.catalog.components.CatalogItem
import com.example.components.HorizontalSpacer
import com.example.domain.events.ProfileEvents
import com.example.profile.components.PageColumn
import com.example.profile.viewmodels.ProfileViewModel
import com.example.ui.R
import com.example.ui.theme.Black
import com.example.ui.theme.LightGrey
import com.example.ui.theme.MainTypography

@Composable
fun FavouritesScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navController: NavController
) {

    val state by viewModel.state.collectAsState()

    val rowCount by rememberSaveable(state.favourites) {
        if (state.favourites.size % 2 == 0) {
            mutableIntStateOf(state.favourites.size / 2)
        } else {
            mutableIntStateOf(state.favourites.size / 2 + 1)
        }
    }

    var selectedItem by rememberSaveable {
        mutableStateOf(FavouriteColumn.PRODUCTS)
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_catalog_arrow_back),
                contentDescription = null,
                tint = Black,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        navController.navigateUp()
                    }
            )

            HorizontalSpacer(width = 28.dp)

            Text(
                text = stringResource(id = R.string.profile_favoutites_title),
                style = MainTypography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp)
                .background(
                    color = LightGrey,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(all = 2.dp)
        ){
            PageColumn(
                modifier = Modifier.weight(0.5f),
                text = stringResource(id = R.string.profile_products),
                isSelected = selectedItem == FavouriteColumn.PRODUCTS,
                onClick = {
                    selectedItem = FavouriteColumn.PRODUCTS
                }
            )
            PageColumn(
                modifier = Modifier.weight(0.5f),
                text = stringResource(id = R.string.profile_brands),
                isSelected = selectedItem == FavouriteColumn.BRANDS,
                onClick = {
                    selectedItem = FavouriteColumn.BRANDS
                }
            )
        }

        when (selectedItem) {
            FavouriteColumn.PRODUCTS -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(7.dp)
                ) {
                    items(rowCount) {
                        val index = it * 2
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(7.dp)
                        ) {

                            val modifier = if (state.favourites.size > index + 1) {
                                Modifier.weight(0.5f)
                            } else Modifier.fillMaxWidth(0.5f)

                            CatalogItem(
                                modifier = modifier,
                                item = state.favourites[index],
                                isFavourite = true,
                                onItemClick = {
                                    viewModel.onEvent(
                                        ProfileEvents.OnFavouritesItemClick(
                                            navController = navController,
                                            itemId = state.favourites[index].id
                                        )
                                    )
                                },
                                onFavourite = {

                                }
                            )

                            if (state.favourites.size > index + 1) {
                                CatalogItem(
                                    modifier = modifier,
                                    item = state.favourites[index + 1],
                                    isFavourite = true,
                                    onItemClick = {
                                        viewModel.onEvent(
                                            ProfileEvents.OnFavouritesItemClick(
                                                navController = navController,
                                                itemId = state.favourites[index + 1].id
                                            )
                                        )
                                    },
                                    onFavourite = {

                                    }
                                )
                            }
                        }
                    }
                }
            }

            FavouriteColumn.BRANDS -> {

            }
        }
    }
}

@Immutable
enum class FavouriteColumn {
    PRODUCTS,BRANDS;
}