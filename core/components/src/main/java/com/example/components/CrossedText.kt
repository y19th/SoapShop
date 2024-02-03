package com.example.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.ui.theme.Grey
import com.example.ui.theme.MainTypography

@Composable
fun CrossedText(
    modifier: Modifier = Modifier,
    title: String,
    contentColor: Color = Grey,
    textStyle: TextStyle = MainTypography.elementText
) {
    Text(
        text = title,
        style = textStyle,
        color = contentColor,
        modifier = Modifier
            .drawWithContent {
                drawContent()
                drawLine(
                    color = contentColor,
                    strokeWidth = 2.dp.value,
                    start = Offset(x = 0f, y = this.size.height * 0.75f),
                    end = Offset(x = this.size.width, y = this.size.height * 0.25f)
                )
            }
            .then(modifier)
    )
}