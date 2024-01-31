package com.example.soapshop.presentation.screens.catalog.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.soapshop.R
import com.example.soapshop.domain.events.CatalogEvents
import com.example.soapshop.domain.models.catalog.CatalogFilter
import com.example.soapshop.ui.theme.DarkGrey
import com.example.soapshop.ui.theme.MainTypography


@Composable
fun CatalogDropDownButton(
    modifier: Modifier = Modifier,
    title: String = "",
    onEvent: (CatalogEvents) -> Unit
) {
    var isExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .wrapContentWidth()
            .clickable { isExpanded = isExpanded.not() }
            .clip(RoundedCornerShape(8.dp))
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_catalog_dropdown),
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = title,
            style = MainTypography.titleRegular,
            color = DarkGrey
        )

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_dropdown_arrow),
            contentDescription = null
        )

        DropdownMenu(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.surface)
            ,
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(id = CatalogFilter.Popular.title),
                        style = MainTypography.titleRegular,
                        color = DarkGrey
                    )
                },
                onClick = {
                    onEvent(
                        CatalogEvents.OnFilterUpdate(newValue = CatalogFilter.Popular)
                    )
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(id = CatalogFilter.PriceDown.title),
                        style = MainTypography.titleRegular,
                        color = DarkGrey
                    )
                },
                onClick = {
                    onEvent(
                        CatalogEvents.OnFilterUpdate(newValue = CatalogFilter.PriceDown)
                    )
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(id = CatalogFilter.PriceUp.title),
                        style = MainTypography.titleRegular,
                        color = DarkGrey
                    )
                },
                onClick = {
                    onEvent(
                        CatalogEvents.OnFilterUpdate(newValue = CatalogFilter.PriceUp)
                    )
                    isExpanded = false
                }
            )
        }
    }
}