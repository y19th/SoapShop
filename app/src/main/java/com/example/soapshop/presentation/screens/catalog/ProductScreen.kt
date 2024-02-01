package com.example.soapshop.presentation.screens.catalog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.soapshop.R
import com.example.soapshop.domain.models.catalog.InfoModel
import com.example.soapshop.domain.models.catalog.PriceModel
import com.example.soapshop.domain.models.catalog.ProductModel
import com.example.soapshop.presentation.components.HorizontalSpacer
import com.example.soapshop.presentation.components.VerticalSpacer
import com.example.soapshop.presentation.viewmodels.CatalogViewModel
import com.example.soapshop.presentation.viewmodels.ProductMap
import com.example.soapshop.ui.theme.Black
import com.example.soapshop.ui.theme.DarkGrey
import com.example.soapshop.ui.theme.Grey
import com.example.soapshop.ui.theme.LightGrey
import com.example.soapshop.ui.theme.LightPink
import com.example.soapshop.ui.theme.MainTypography
import com.example.soapshop.ui.theme.Orange
import com.example.soapshop.ui.theme.Pink
import com.example.soapshop.ui.theme.White
import com.example.soapshop.util.extension.isOverflowed
import com.example.soapshop.util.extension.withUnit

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
                tint = Black,
                modifier = Modifier.padding(horizontal = 5.dp)
            )

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_catalog_send),
                contentDescription =  null,
                tint = Black
            )
        }

        ExpandedProductItem(
            item = state.products.find { productModel -> productModel.id == productId } ?: state.products[0]
        )

    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpandedProductItem(
   item: ProductModel
) {
    val images by rememberSaveable(item) {
        mutableStateOf(ProductMap.map[item.id])
    }

    val pagerState = rememberPagerState(
        pageCount = {
            images?.size ?: 0
        }
    )
    val isOverflowed by rememberSaveable {
        mutableStateOf(item.ingredients.isOverflowed(12.dp))
    }
    var descriptionVisible by rememberSaveable {
        mutableStateOf(true)
    }
    var ingredientVisible by rememberSaveable {
        mutableStateOf(!isOverflowed)
    }


    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 21.dp, end = 14.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalPager(state = pagerState) {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(
                                id = images?.get(it) ?: R.drawable.ic_body_lotion
                            ),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.align(Alignment.Center)
                        )
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_question_mark),
                            contentDescription = null,
                            tint = White,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(start = 5.dp, bottom = 21.dp)
                                .background(
                                    color = LightGrey,
                                    shape = CircleShape
                                )
                                .clip(CircleShape)
                                .clickable {
                                    /*TODO*/
                                }
                                .padding(vertical = 3.dp, horizontal = 5.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    repeat(pagerState.pageCount) { iteration ->
                        val color =
                            if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary
                            else LightGrey

                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(6.dp)
                        )
                    }
                }
            }
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_catalog_heart),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .clip(CircleShape)
                    .padding(end = 2.dp)
                    .clickable {
                        /*TODO*/
                    }
            )
        }

        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
        ) {
            Text(
                text = item.title,
                style = MainTypography.titleLarge,
                color = Grey
            )

            VerticalSpacer(height = 6.dp)

            Text(
                text = item.subtitle,
                style = MainTypography.largeTitle,
                color = Black
            )

            VerticalSpacer(height = 10.dp)

            Text(
                text = pluralStringResource(
                    id = R.plurals.items_access,
                    count = item.available,
                    item.available
                ),
                style = MainTypography.textMedium,
                color = Grey
            )

            Divider(
                modifier = Modifier.padding(vertical = 10.dp),
                thickness = 1.dp,
                color = LightGrey
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ReviewsBar(
                    reviewMark = item.feedback.rating
                )

                HorizontalSpacer(width = 8.dp)

                Text(
                    text = item.feedback.rating.toString(),
                    style = MainTypography.textMedium,
                    color = Grey
                )

                Box(
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        .size(2.dp)
                        .background(color = Grey)
                )
                
                Text(
                    text = pluralStringResource(
                        id = R.plurals.reviews,
                        count = item.feedback.count,
                        item.feedback.count
                    ),
                    style = MainTypography.textMedium,
                    color = Grey
                )
            }
            Row (
                modifier = Modifier.padding(start = 5.dp,end = 5.dp,top = 16.dp, bottom = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(11.dp)
            ){
                Text(
                    text = item.price.priceWithDiscount.withUnit(item.price.unit),
                    style = MainTypography.priceText,
                    color = Black
                )
                CrossedText(
                    title = item.price.price.withUnit(item.price.unit)
                )

                DiscountText(
                    title = item.price.discount
                )
            }
            ProductSection(
                title = stringResource(id = R.string.product_section_desc)
            ) {
                AnimatedVisibility(visible = descriptionVisible) {
                    Column {
                        ContainerText(
                            text = item.title
                        )

                        VerticalSpacer(height = 8.dp)

                        Text(
                            text = item.description,
                            style = MainTypography.textMedium,
                            color = Black
                        )

                        VerticalSpacer(height = 10.dp)
                    }
                }

                Text(
                    text = stringResource(
                        id = if(!descriptionVisible)R.string.additional_button
                        else R.string.hide_button
                    ),
                    style = MainTypography.buttonSmall,
                    color = Grey,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            descriptionVisible = descriptionVisible.not()
                        }
                )
            }
            ProductSection(
                modifier = Modifier.padding(vertical = 34.dp),
                title = stringResource(id = R.string.product_section_attr)
            ) {
                item.info.forEach {
                    InfoSection(item = it)
                }
            }
            ProductSection(
                modifier = Modifier.padding(bottom = 32.dp),
                title = stringResource(id = R.string.product_section_ingr),
                trailingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_product_copy),
                        contentDescription = null,
                        tint = Grey
                    )
                }
            ) {
                Text(
                    text = item.ingredients,
                    style = MainTypography.textMedium,
                    maxLines = if(ingredientVisible) Int.MAX_VALUE else 2,
                    color = DarkGrey,
                    overflow = if(ingredientVisible) TextOverflow.Clip else TextOverflow.Ellipsis
                )

                VerticalSpacer(height = 10.dp)

                if(isOverflowed) {
                    Text(
                        text = stringResource(
                            id = if (!ingredientVisible) R.string.additional_button
                            else R.string.hide_button
                        ),
                        style = MainTypography.buttonSmall,
                        color = Grey,
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable {
                                ingredientVisible = ingredientVisible.not()
                            }
                    )
                }
            }
            ProductBuyButton(
                priceModel = item.price,
                onClick = {
                    /*TODO*/
                }
            )
        }
    }
}

@Composable
fun ProductBuyButton(
    modifier: Modifier = Modifier,
    priceModel: PriceModel,
    onClick: () -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Pink,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 16.dp, vertical = 17.dp)
            .clickable { onClick.invoke() }
            .clip(RoundedCornerShape(8.dp))
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = priceModel.priceWithDiscount,
                style = MainTypography.buttonMedium,
                color = White
            )
            CrossedText(
                title = priceModel.price,
                contentColor = LightPink,
                textStyle = MainTypography.caption
            )
        }
        Text(
            text = stringResource(id = R.string.add_to_cart),
            style = MainTypography.buttonMedium,
            color = White
        )
    }
}


@Composable
fun InfoSection(
    item: InfoModel
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = item.title,
            style = MainTypography.textMedium,
            color = DarkGrey
        )
        Text(
            text = item.value,
            style = MainTypography.textMedium,
            color = DarkGrey
        )
    }
    Divider(
        thickness = 1.dp,
        color = LightGrey
    )
}

@Composable
fun ContainerText(
    modifier: Modifier = Modifier,
    text: String = ""
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = LightGrey,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .clickable { /*TODO*/ }
            .padding(all = 8.dp)
            .then(modifier)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            style = MainTypography.titleMedium,
            color = Black
        )
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_next),
            contentDescription = null,
            tint = Black
        )
    }
}

@Stable
@Composable
fun ProductSection(
    modifier: Modifier = Modifier,
    title: String = "",
    trailingIcon: @Composable RowScope.() -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = title,
                style = MainTypography.titleLarge,
                color = Black
            )
            trailingIcon.invoke(this)
        }

        VerticalSpacer(height = 16.dp)

        content.invoke(this)
    }
}


@Stable
@Composable
fun ReviewsBar(
    reviewMark: Float = 0f
) {
    val filledStars by rememberSaveable {
        mutableIntStateOf(reviewMark.toInt())
    }
    val semiStars by rememberSaveable {
        mutableIntStateOf(
            if(reviewMark - filledStars > 0f) 1
            else 0
        )
    }
    val emptyStars by rememberSaveable {
        mutableIntStateOf(5 - filledStars - semiStars)
    }
    Row {
        repeat(filledStars) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_full_star),
                contentDescription = null,
                tint = Orange
            )
        }
        repeat(semiStars) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_semi_star),
                contentDescription = null,
                tint = Orange
            )
        }
        repeat(emptyStars) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_empty_star),
                contentDescription = null,
                tint = Orange
            )
        }
    }
}