package com.example.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ui.theme.Black
import com.example.ui.theme.Grey
import com.example.ui.theme.LightGrey
import com.example.ui.theme.MainTypography
import com.example.ui.theme.White

@Composable
internal fun PageColumn(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    text: String = "",
    onClick: () -> Unit
) {
    Text(
        text = text,
        style = if(isSelected) MainTypography.buttonMedium else MainTypography.titleRegular,
        color = if(isSelected) Black else Grey,
        modifier = modifier
            .background(
                color = if (isSelected) White else LightGrey,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                onClick.invoke()
            }
            .padding(vertical = 9.dp)
            .clip(RoundedCornerShape(8.dp)),
        textAlign = TextAlign.Center
    )
}