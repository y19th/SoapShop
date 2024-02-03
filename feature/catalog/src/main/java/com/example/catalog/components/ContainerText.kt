package com.example.catalog.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.example.ui.theme.Black
import com.example.ui.theme.LightGrey
import com.example.ui.theme.MainTypography

@Stable
@Composable
internal fun ContainerText(
    modifier: Modifier = Modifier,
    text: String = ""
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = LightGrey,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .clickable { /*TODO*/ }
            .padding(all = 8.dp)
            .then(modifier)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            style = MainTypography.titleMedium,
            color = Black
        )
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_next),
            contentDescription = null,
            tint = Black
        )
    }
}