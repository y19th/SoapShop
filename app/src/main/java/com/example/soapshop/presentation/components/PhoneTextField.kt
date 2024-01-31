package com.example.soapshop.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.soapshop.R
import com.example.soapshop.ui.theme.MainTypography
import com.example.soapshop.ui.theme.disabled
import com.example.soapshop.ui.theme.onDisabled
import com.example.soapshop.util.PhoneMask
import com.example.soapshop.util.PhoneVisualTransformation
import com.example.soapshop.util.extension.errorBorder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneTextField(
    modifier: Modifier = Modifier,
    value: String,
    isEnabled: Boolean = true,
    isError: Boolean = false,
    errorText: String = stringResource(id = R.string.phone_error),
    onValueChange: (String) -> Unit,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    placeHolderText: String = "",
    mask: PhoneMask? = null
) {
    Column {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .then(modifier),
            value = value,
            onValueChange = {
                if (mask != null) {
                    onValueChange.invoke(it.take(mask.mask.count { it == mask.maskNum }))
                } else onValueChange.invoke(it)
            },
            textStyle = MainTypography.placeHolderText,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            visualTransformation = if (mask != null) PhoneVisualTransformation(mask) else VisualTransformation.None
        ) { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = value,
                innerTextField = innerTextField,
                enabled = isEnabled,
                singleLine = true,
                isError = isError,
                shape = shape,
                contentPadding = PaddingValues(
                    top = 11.dp,
                    bottom = 11.dp,
                    start = 16.dp,
                    end = 8.dp
                ),
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
                            .errorBorder(
                                isError = isError,
                                color = MaterialTheme.colorScheme.error,
                                shape = shape
                            )
                            .clip(shape)
                    )
                },
                trailingIcon = {
                    if(value.isNotEmpty()) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_tralling_cancel),
                            contentDescription = null,
                            modifier = Modifier
                                .clip(CircleShape)
                                .clickable { onValueChange.invoke("") }
                        )
                    }
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
        if(isError) {
            Text(
                text = errorText,
                style = MainTypography.titleSmall,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}