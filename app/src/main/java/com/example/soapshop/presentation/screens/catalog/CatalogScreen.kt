package com.example.soapshop.presentation.screens.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.soapshop.R
import com.example.soapshop.domain.events.CatalogEvents
import com.example.soapshop.domain.models.catalog.CatalogFilter
import com.example.soapshop.presentation.components.VerticalSpacer
import com.example.soapshop.presentation.screens.catalog.components.CatalogDropDownButton
import com.example.soapshop.presentation.viewmodels.CatalogViewModel
import com.example.soapshop.ui.theme.DarkGrey
import com.example.soapshop.ui.theme.MainTypography

@Composable
fun CatalogScreen(
    viewModel: CatalogViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.bar_catalog),
            style = MainTypography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        VerticalSpacer(height = 22.dp)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CatalogDropDownButton(
                title = stringResource(id = state.filter.title),
                onEvent = viewModel::onEvent
            )
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_catalog_filter),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(4.dp))
                
                Text(
                    text = stringResource(id = R.string.catalog_filter),
                    style = MainTypography.titleRegular,
                    color = DarkGrey
                )
            }
        }
    }
}

