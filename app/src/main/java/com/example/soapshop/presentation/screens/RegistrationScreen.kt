package com.example.soapshop.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.soapshop.R
import com.example.soapshop.domain.events.RegistrationEvents
import com.example.soapshop.presentation.viewmodels.RegistrationViewModel
import com.example.soapshop.ui.theme.MainTypography
import com.example.soapshop.ui.theme.disabled
import com.example.soapshop.ui.theme.onDisabled

@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsState()

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
            modifier = Modifier.fillMaxWidth().weight(0.68f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ColoredTextField(
                value = state.name,
                onValueChange = {
                    viewModel.onEvent(RegistrationEvents.OnNameChange(newValue = it))
                },
                isEnabled = true,
                placeHolderText = stringResource(id = R.string.placeholder_name)
            )

            ColoredTextField(
                value = state.surname,
                onValueChange = { viewModel.onEvent(RegistrationEvents.OnSurnameChange(newValue = it)) },
                isEnabled = true,
                placeHolderText = stringResource(id = R.string.placeholder_surname)
            )

            ColoredTextField(
                value = state.phone,
                onValueChange = { viewModel.onEvent(RegistrationEvents.OnPhoneChange(newValue = it)) },
                isEnabled = true,
                placeHolderText = stringResource(id = R.string.placeholder_phone)
            )
            FilledButton(
                modifier = Modifier.padding(vertical = 16.dp),
                text = stringResource(id = R.string.registration_button_enter),
                onClick = {}
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

@Composable
fun FilledButton(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
        ,
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(top = 18.dp,bottom = 15.dp),
        onClick = onClick
    ) {
        Text(
            text = text,
            style = MainTypography.buttonMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColoredTextField(
    modifier: Modifier = Modifier,
    value: String,
    isEnabled: Boolean = true,
    onValueChange: (String) -> Unit,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    placeHolderText: String = ""
    ) {

    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .then(modifier),
        value = value,
        onValueChange = onValueChange,
        textStyle = MainTypography.placeHolderText
    ) { innerTextField ->  
        TextFieldDefaults.DecorationBox(
            value = value,
            innerTextField = innerTextField,
            enabled = isEnabled,
            singleLine = true,
            shape = shape,
            contentPadding = PaddingValues(top = 11.dp, bottom = 11.dp, start = 16.dp, end = 8.dp),
            visualTransformation = VisualTransformation.None,
            interactionSource = remember { MutableInteractionSource() },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = MaterialTheme.colorScheme.onDisabled,
                focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledTextColor = MaterialTheme.colorScheme.onDisabled,
                unfocusedContainerColor = MaterialTheme.colorScheme.disabled,
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                disabledContainerColor = MaterialTheme.colorScheme.disabled
            ),
            container = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = shape
                        )
                        .clip(shape)
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_tralling_cancel),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { onValueChange.invoke("") }
                )
            },
            placeholder = {
                Text(
                    text = placeHolderText,
                    style = MainTypography.placeHolderText,
                    color = MaterialTheme.colorScheme.onDisabled
                )
            }
        )
    }
}