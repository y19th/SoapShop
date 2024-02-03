package com.example.util.extension

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


fun Modifier.errorBorder(
    isError: Boolean,
    shape: RoundedCornerShape,
    color: Color
): Modifier {
    return if(isError) {
        this.border(
            width = 1.dp,
            color = color,
            shape = shape
        )
    } else this
}

fun Modifier.padding(
    horizontal: Dp,
    top: Dp,
    bottom: Dp,
) = this.padding(
    start = horizontal,
    end = horizontal,
    top = top,
    bottom = bottom
)