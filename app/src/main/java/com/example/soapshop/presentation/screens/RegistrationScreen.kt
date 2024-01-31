package com.example.soapshop.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.soapshop.R
import com.example.soapshop.domain.events.RegistrationEvents
import com.example.soapshop.presentation.components.ColoredTextField
import com.example.soapshop.presentation.components.FilledButton
import com.example.soapshop.presentation.components.PhoneTextField
import com.example.soapshop.presentation.viewmodels.RegistrationViewModel
import com.example.soapshop.ui.theme.MainTypography
import com.example.soapshop.ui.theme.onDisabled
import com.example.soapshop.util.PhoneMask

@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel = hiltViewModel(),
    navController: NavController = rememberNavController()
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(null) {
        viewModel.onEvent(RegistrationEvents.OnCheckAuth(navController))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.registration_enter),
            style = MainTypography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(0.22f)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.68f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ColoredTextField(
                value = state.name,
                onValueChange = { viewModel.onEvent(RegistrationEvents.OnNameChange(newValue = it)) },
                isError = state.isNameError,
                errorText = stringResource(
                    R.string.text_error,
                    stringResource(id = R.string.placeholder_name)
                ),
                isEnabled = true,
                placeHolderText = stringResource(id = R.string.placeholder_name)
            )

            ColoredTextField(
                value = state.surname,
                onValueChange = { viewModel.onEvent(RegistrationEvents.OnSurnameChange(newValue = it)) },
                isEnabled = true,
                isError = state.isSurnameError,
                errorText = stringResource(
                    R.string.text_error,
                    stringResource(id = R.string.placeholder_surname)
                ),
                placeHolderText = stringResource(id = R.string.placeholder_surname)
            )
            PhoneTextField(
                value = state.phone,
                onValueChange = { newValue ->
                    if(newValue.isDigitsOnly()) {
                        viewModel.onEvent(RegistrationEvents.OnPhoneChange(newValue = newValue))
                    }
                },
                isEnabled = true,
                isError = state.isPhoneError,
                placeHolderText = stringResource(id = R.string.placeholder_phone),
                mask = PhoneMask()
            )

            FilledButton(
                modifier = Modifier.padding(vertical = 16.dp),
                text = stringResource(id = R.string.registration_button_enter),
                isEnabled = state.isValid,
                onClick = { viewModel.onEvent(RegistrationEvents.OnLogin) }
            )
        }
        
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = stringResource(id = R.string.registration_terms),
                style = MainTypography.caption,
                color = MaterialTheme.colorScheme.onDisabled
            )
            Text(
                text = stringResource(id = R.string.registration_underlined_terms),
                style = MainTypography.linkText,
                color = MaterialTheme.colorScheme.onDisabled
            )
        }
    }
}

