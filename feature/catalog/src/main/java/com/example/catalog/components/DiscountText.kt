package com.example.catalog.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.theme.MainTypography
import com.example.ui.theme.White

@Composable
internal fun DiscountText(
    title: String = ""
) {
    Text(
        text = "-$title%",
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