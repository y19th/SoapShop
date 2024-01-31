package com.example.soapshop.presentation.screens.catalog

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.soapshop.R
import com.example.soapshop.domain.events.CatalogEvents
import com.example.soapshop.domain.models.catalog.CatalogPinModel
import com.example.soapshop.presentation.components.HorizontalSpacer
import com.example.soapshop.presentation.components.VerticalSpacer
import com.example.soapshop.presentation.screens.catalog.components.CatalogDropDownButton
import com.example.soapshop.presentation.viewmodels.CatalogViewModel
import com.example.soapshop.ui.theme.Black
import com.example.soapshop.ui.theme.DarkBlue
import com.example.soapshop.ui.theme.DarkGrey
import com.example.soapshop.ui.theme.Grey
import com.example.soapshop.ui.theme.LightGrey
import com.example.soapshop.ui.theme.MainTypography
import com.example.soapshop.ui.theme.Orange
import com.example.soapshop.ui.theme.White

@Composable
fun CatalogScreen(
    viewModel: CatalogViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.bar_catalog),
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
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
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
                .padding(top = 16.dp, bottom = 32.dp)
            ,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(state.pinList) {model ->
                CatalogPin(
                    pinModel = model,
                    isSelected = model == state.selectedPin,
                    onEvent = viewModel::onEvent
                )
            }
        }
        Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
            Row (
                horizontalArrangement = Arrangement.spacedBy(7.dp)
            ){
                CatalogItem(
                    modifier = Modifier.weight(0.5f)
                )
                CatalogItem(
                    modifier = Modifier.weight(0.5f)
                )
            }
            Row (
                horizontalArrangement = Arrangement.spacedBy(7.dp)
            ){
                CatalogItem(
                    modifier = Modifier.weight(0.5f)
                )
                CatalogItem(
                    modifier = Modifier.weight(0.5f)
                )
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CatalogItem(
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(
        pageCount = { 2 }
    )

    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(8.dp)
            )
            .then(modifier)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(horizontal = 7.dp)
        ) {
            HorizontalPager(state = pagerState) {
                Image(
                    painter = painterResource(id = R.drawable.ic_deep_clean),
                    contentDescription = null
                )
            }
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                ,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary
                        else LightGrey

                    Box(modifier = Modifier
                        .padding(1.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(4.dp)
                    )
                }
            }
            CrossedText(
                modifier = Modifier.padding(vertical = 3.dp),
                title = "749 P"
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "489 P",
                    style = MainTypography.titleMedium,
                    color = Black
                )

                Text(
                    text = "-35%",
                    style = MainTypography.elementText,
                    color = White,
                    modifier = Modifier
                        .padding(vertical = 1.dp)

                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(vertical = 3.dp, horizontal = 6.dp)
                )
            }

            Text(
                text = "ESFOLIO",
                style = MainTypography.titleSmall,
                color = Black,
                modifier = Modifier.padding(vertical = 2.dp)
            )

            Text(
                text = "Лосьон для тела`ESFOLIO` COENZYME Q 10 Увлажняющий 500 мл",
                style = MainTypography.caption,
                color = DarkGrey
            )

            VerticalSpacer(height = 4.dp)

            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .offset(x = (-3).dp)
                ,
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_catalog_star),
                    contentDescription = null,
                    tint = Orange
                )
                Text(
                    text = "4.5",
                    style = MainTypography.elementText,
                    color = Orange
                )
                Text(
                    text = "(51)",
                    style = MainTypography.elementText,
                    color = Grey
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = 7.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_catalog_add),
                    contentDescription = null,
                    tint = White,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(topStart = 8.dp, bottomEnd = 8.dp)
                        )
                        .padding(all = 4.dp)
                        .clip(RoundedCornerShape(topStart = 8.dp, bottomEnd = 8.dp))
                        .clickable {
                            /*TODO*/
                        }
                )
            }

        }
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_catalog_heart),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clip(CircleShape)
                .padding(all = 6.dp)
                .clickable {
                    /*TODO*/
                }
        )
    }
}


@Composable
fun CrossedText(
    modifier: Modifier = Modifier,
    title: String
) {
    Text(
        text = title,
        style = MainTypography.elementText,
        color = Grey,
        modifier = Modifier
            .drawWithContent {
                drawContent()
                drawLine(
                    color = Grey,
                    strokeWidth = 2.dp.value,
                    start = Offset(x = 0f, y = this.size.height * 0.75f),
                    end = Offset(x = this.size.width, y = this.size.height * 0.25f)
                )
            }
            .then(modifier)
    )
}



@Composable
fun CatalogPin(
    pinModel: CatalogPinModel,
    isSelected: Boolean = false,
    onEvent: (CatalogEvents) -> Unit
) {
    Row(
       modifier = Modifier
           .background(
               color = if (isSelected) DarkBlue else LightGrey,
               shape = CircleShape
           )
           .clip(CircleShape)
           .clickable {
               onEvent.invoke(CatalogEvents.OnPinSelected(newValue = pinModel))
           }

    ){
        Text(
            text = pinModel.title,
            style = MainTypography.titleRegular,
            color = if(isSelected) White else Grey,
            modifier = Modifier.padding(
                top = 4.dp,
                bottom = 4.dp,
                start = 12.dp,
                end = if(isSelected) 0.dp else 12.dp
            )
        )
        if(isSelected) {
            HorizontalSpacer(width = 4.dp)

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_pin_cancel),
                contentDescription = null,
                tint = White,
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 4.dp, end = 8.dp)
                    .clip(CircleShape)
                    .clickable {
                        onEvent.invoke(CatalogEvents.OnPinCancel)
                    }
            )
        }
    }
}
