package com.example.catalog.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.components.VerticalSpacer
import com.example.ui.theme.Black
import com.example.ui.theme.MainTypography

@Stable
@Composable
internal fun ProductSection(
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
