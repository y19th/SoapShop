package com.example.soapshop.presentation.screens.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.soapshop.R
import com.example.soapshop.domain.events.ProfileEvents
import com.example.soapshop.presentation.components.FilledButton
import com.example.soapshop.presentation.components.HorizontalSpacer
import com.example.soapshop.presentation.components.VerticalSpacer
import com.example.soapshop.presentation.viewmodels.ProfileViewModel
import com.example.soapshop.ui.theme.Black
import com.example.soapshop.ui.theme.DarkGreyDarker
import com.example.soapshop.ui.theme.Grey
import com.example.soapshop.ui.theme.LightGrey
import com.example.soapshop.ui.theme.MainTypography
import com.example.soapshop.ui.theme.Orange
import com.example.soapshop.ui.theme.Pink
import com.example.soapshop.util.extension.padding
import com.example.soapshop.util.extension.withPhoneMask

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navController: NavController = rememberNavController(),
) {

    val state by viewModel.state.collectAsState()

    SideEffect {
        viewModel.refreshFavourites()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.profile_title),
            style = MainTypography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        VerticalSpacer(height = 34.dp)

        ProfileItem(
            leadingIcon = ProfileIcon(
                res = R.drawable.ic_profile_user,
                tint = DarkGreyDarker
            ),
            trailingIcon = ProfileIcon(
                res = R.drawable.ic_profile_enter,
                tint = DarkGreyDarker
            ),
            titleText = "${state.user.name} ${state.user.surname}",
            additionalText = state.user.phone.withPhoneMask(),
            onClick = {
                /*TODO*/
            }
        )

        VerticalSpacer(height = 24.dp)

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ProfileItem(
                leadingIcon = ProfileIcon(
                    res = R.drawable.ic_catalog_heart,
                    tint = Pink
                ),
                titleText = stringResource(id = R.string.profile_favoutites_title),
                additionalText = pluralStringResource(
                    id = R.plurals.products,
                    count = state.favourites.size,
                    state.favourites.size
                ),
                onClick = {
                    /*TODO*/
                }
            )
            ProfileItem(
                leadingIcon = ProfileIcon(
                    res = R.drawable.ic_profile_shops,
                    tint = Pink
                ),
                titleText = stringResource(id = R.string.profile_shops),
                onClick = {
                    /*TODO*/
                }
            )
            ProfileItem(
                leadingIcon = ProfileIcon(
                    res = R.drawable.ic_profile_feedback,
                    tint = Orange
                ),
                titleText = stringResource(id = R.string.profile_feedback),
                onClick = {
                    /*TODO*/
                }
            )
            ProfileItem(
                leadingIcon = ProfileIcon(
                    res = R.drawable.ic_profile_offer,
                    tint = Grey
                ),
                titleText = stringResource(id = R.string.profile_offer),
                onClick = {
                    /*TODO*/
                }
            )
            ProfileItem(
                leadingIcon = ProfileIcon(
                    res = R.drawable.ic_profile_products_back,
                    tint = Grey
                ),
                titleText = stringResource(id = R.string.profile_products_back),
                onClick = {
                    /*TODO*/
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        FilledButton(
            modifier = Modifier.padding(bottom = 32.dp),
            text = stringResource(id = R.string.profile_exit),
            containerColor = LightGrey,
            textColor = Black
        ) {
            viewModel.onEvent(ProfileEvents.OnUserExit(navController))
        }
    }
}

@Composable
fun ProfileItem(
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
data class ProfileIcon(
    @DrawableRes val res: Int,
    val tint: Color
)