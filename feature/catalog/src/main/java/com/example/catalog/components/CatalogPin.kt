package com.example.catalog.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.components.HorizontalSpacer
import com.example.domain.events.CatalogEvents
import com.example.domain.models.catalog.CatalogPinModel
import com.example.ui.R
import com.example.ui.theme.DarkBlue
import com.example.ui.theme.Grey
import com.example.ui.theme.LightGrey
import com.example.ui.theme.MainTypography
import com.example.ui.theme.White

@Composable
internal fun CatalogPin(
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
