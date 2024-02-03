package com.example.profile.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.components.HorizontalSpacer
import com.example.ui.R
import com.example.ui.theme.Black
import com.example.ui.theme.Grey
import com.example.ui.theme.LightGrey
import com.example.ui.theme.MainTypography
import com.example.util.extension.padding

@Stable
@Composable
internal fun ProfileItem(
    modifier: Modifier = Modifier,
    leadingIcon: ProfileIcon,
    trailingIcon: ProfileIcon = ProfileIcon(res = R.drawable.ic_arrow_next, tint = Black),
    titleText: String = "",
    additionalText: String? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = LightGrey,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick.invoke() }
            .padding(horizontal = 8.dp, top = 8.dp, bottom = 9.dp)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = leadingIcon.res),
            contentDescription = null,
            tint = leadingIcon.tint
        )

        HorizontalSpacer(width = 16.dp)

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = titleText,
                style = MainTypography.titleMedium,
                color = Black
            )
            if(!additionalText.isNullOrEmpty()) {
                Text(
                    text = additionalText,
                    style = MainTypography.caption,
                    color = Grey
                )
            }
        }

        Icon(
            imageVector = ImageVector.vectorResource(id = trailingIcon.res),
            contentDescription = null,
            tint = trailingIcon.tint
        )
    }
}


@Immutable
internal data class ProfileIcon(
    @DrawableRes val res: Int,
    val tint: Color
)