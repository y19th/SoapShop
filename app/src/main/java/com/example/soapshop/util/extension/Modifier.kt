package com.example.soapshop.util.extension

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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