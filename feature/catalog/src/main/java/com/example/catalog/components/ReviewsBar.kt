package com.example.catalog.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.ui.R
import com.example.ui.theme.Orange

@Stable
@Composable
fun ReviewsBar(
    reviewMark: Float = 0f
) {
    val filledStars by rememberSaveable {
        mutableIntStateOf(reviewMark.toInt())
    }
    val semiStars by rememberSaveable {
        mutableIntStateOf(
            if(reviewMark - filledStars > 0f) 1
            else 0
        )
    }
    val emptyStars by rememberSaveable {
        mutableIntStateOf(5 - filledStars - semiStars)
    }
    Row {
        repeat(filledStars) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_full_star),
                contentDescription = null,
                tint = Orange
            )
        }
        repeat(semiStars) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_semi_star),
                contentDescription = null,
                tint = Orange
            )
        }
        repeat(emptyStars) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_empty_star),
                contentDescription = null,
                tint = Orange
            )
        }
    }
}