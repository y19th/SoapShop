package com.example.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.catalog.components.CatalogDropDownButton
import com.example.catalog.components.CatalogItem
import com.example.catalog.components.CatalogPin
import com.example.catalog.viewmodels.CatalogViewModel
import com.example.components.HorizontalSpacer
import com.example.components.VerticalSpacer
import com.example.domain.events.CatalogEvents
import com.example.domain.models.catalog.CatalogTag
import com.example.ui.R
import com.example.ui.theme.DarkGrey
import com.example.ui.theme.MainTypography

@Composable
fun CatalogScreen(
    viewModel: CatalogViewModel = hiltViewModel(),
    navController: NavController = rememberNavController()
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(null) {
        viewModel.refreshData()
    }


    val filtered by rememberSaveable(state.selectedPin, state.products) {
        mutableStateOf(
            with(state) {
                if (selectedPin.tag is CatalogTag.All || selectedPin.tag is CatalogTag.Deleted) {
                    products
                } else {
                    products.filter { productModel ->
                        productModel.tags.contains(selectedPin.tag.tag)
                    }
                }
            }
        )
    }
    val rowCount by rememberSaveable(filtered) {
        if (filtered.size % 2 == 0) {
            mutableIntStateOf(filtered.size / 2)
        } else {
            mutableIntStateOf(filtered.size / 2 + 1)
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.title_catalog),
            style = MainTypography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        VerticalSpacer(height = 22.dp)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CatalogDropDownButton(
                title = stringResource(id = state.filter.title),
                onEvent = viewModel::onEvent
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_catalog_filter),
                    contentDescription = null
                )

                HorizontalSpacer(width = 4.dp)

                Text(
                    text = stringResource(id = R.string.catalog_filter),
                    style = MainTypography.titleRegular,
                    color = DarkGrey
                )
            }
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(state.pinList) { model ->
                CatalogPin(
                    pinModel = model,
                    isSelected = model == state.selectedPin,
                    onEvent = viewModel::onEvent
                )
            }
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            if (filtered.isNotEmpty()) {
                items(rowCount) {
                    val index = it * 2
                    Row(
                        modifier = Modifier.fillMaxHeight(0.1f),
                        horizontalArrangement = Arrangement.spacedBy(7.dp)
                    ) {
                        CatalogItem(
                            modifier = Modifier.weight(0.5f),
                            item = filtered[index],
                            isFavourite = state.favourites.contains(filtered[index].id),
                            onItemClick = {
                                viewModel.onEvent(
                                    CatalogEvents.OnItemClick(
                                        itemId = filtered[index].id,
                                        navController = navController
                                    )
                                )
                            },
                            onFavourite = {
                                viewModel.onEvent(
                                    CatalogEvents.OnFavourite(
                                        model = filtered[index]
                                    )
                                )
                            }
                        )

                        if (filtered.size > index + 1) {
                            CatalogItem(
                                modifier = Modifier.weight(0.5f),
                                item = filtered[index + 1],
                                isFavourite = state.favourites.contains(filtered[index + 1].id),
                                onItemClick = {
                                    viewModel.onEvent(
                                        CatalogEvents.OnItemClick(
                                            itemId = filtered[index + 1].id,
                                            navController = navController
                                        )
                                    )
                                },
                                onFavourite = {
                                    viewModel.onEvent(
                                        CatalogEvents.OnFavourite(
                                            model = filtered[index + 1]
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
