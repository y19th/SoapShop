package com.example.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.components.FilledButton
import com.example.components.VerticalSpacer
import com.example.domain.events.ProfileEvents
import com.example.profile.components.ProfileIcon
import com.example.profile.components.ProfileItem
import com.example.ui.R
import com.example.ui.theme.Black
import com.example.ui.theme.DarkGreyDarker
import com.example.ui.theme.Grey
import com.example.ui.theme.LightGrey
import com.example.ui.theme.MainTypography
import com.example.ui.theme.Orange
import com.example.ui.theme.Pink
import com.example.util.extension.withPhoneMask

@Composable
fun ProfileScreen(
    viewModel: com.example.profile.viewmodels.ProfileViewModel = hiltViewModel(),
    navController: NavController = rememberNavController(),
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(null) {
        viewModel.refreshData()
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
                    viewModel.onEvent(ProfileEvents.OnFavouriteClick(navController))
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


