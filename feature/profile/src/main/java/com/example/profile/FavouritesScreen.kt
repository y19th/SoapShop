package com.example.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.catalog.components.CatalogItem
import com.example.ui.R
import com.example.domain.events.ProfileEvents
import com.example.components.HorizontalSpacer
import com.example.components.VerticalSpacer
import com.example.profile.viewmodels.ProfileViewModel

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
                tint = com.example.ui.theme.Black,
                modifier = Modifier.clickable {
                    navController.navigateUp()
                }
            )

            HorizontalSpacer(width = 28.dp)

            Text(
                text = stringResource(id = R.string.profile_favoutites_title),
                style = com.example.ui.theme.MainTypography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        VerticalSpacer(height = 62.dp)

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            items(rowCount) {
                val index = it * 2
                Row(
                    modifier = Modifier.fillMaxHeight(0.1f),
                    horizontalArrangement = Arrangement.spacedBy(7.dp)
                ) {
                    CatalogItem(
                        modifier = Modifier.weight(0.5f),
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
                            modifier = Modifier.weight(0.5f),
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
}