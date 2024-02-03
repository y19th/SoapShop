package com.example.catalog.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.catalog.viewmodels.ProductMap
import com.example.components.CrossedText
import com.example.components.VerticalSpacer
import com.example.domain.models.catalog.ProductModel
import com.example.ui.R
import com.example.ui.theme.Black
import com.example.ui.theme.DarkGrey
import com.example.ui.theme.Grey
import com.example.ui.theme.LightGrey
import com.example.ui.theme.MainTypography
import com.example.ui.theme.Orange
import com.example.ui.theme.White
import com.example.util.extension.withUnit

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CatalogItem(
    modifier: Modifier = Modifier,
    item: ProductModel,
    isFavourite: Boolean = false,
    onItemClick: (String) -> Unit,
    onFavourite: () -> Unit
) {
    val images by rememberSaveable(item) {
        mutableStateOf(ProductMap.map[item.id])
    }
    val icon = if(isFavourite) R.drawable.ic_filled_heart else R.drawable.ic_catalog_heart

    val pagerState = rememberPagerState(
        pageCount = {
            images?.size ?: 0
        }
    )

    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                onItemClick.invoke(item.id)
            }
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
                    painter = painterResource(id = images?.get(it) ?: R.drawable.ic_body_lotion),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
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
                title = item.price.price.withUnit(item.price.unit)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.price.priceWithDiscount.withUnit(item.price.unit),
                    style = MainTypography.titleMedium,
                    color = Black
                )

                DiscountText(
                    title = item.price.discount
                )
            }

            Text(
                text = item.title,
                style = MainTypography.titleSmall,
                color = Black,
                modifier = Modifier.padding(vertical = 2.dp)
            )

            Text(
                text = item.subtitle,
                style = MainTypography.caption,
                color = DarkGrey,
                modifier = Modifier.heightIn(
                    min = 40.dp
                )
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
                    text = item.feedback.rating.toString(),
                    style = MainTypography.elementText,
                    color = Orange
                )
                Text(
                    text = "(${item.feedback.count})",
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
                        .clickable {
                            /*TODO*/
                        }
                        .padding(all = 4.dp)
                        .clip(RoundedCornerShape(topStart = 8.dp, bottomEnd = 8.dp))

                )
            }

        }
        Icon(
            imageVector = ImageVector.vectorResource(id = icon),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clip(CircleShape)
                .clickable {
                    onFavourite.invoke()
                }
                .padding(all = 6.dp)
        )
    }
}