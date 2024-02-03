package com.example.catalog.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domain.models.catalog.InfoModel
import com.example.ui.theme.DarkGrey
import com.example.ui.theme.LightGrey
import com.example.ui.theme.MainTypography

@Composable
internal fun InfoSection(
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