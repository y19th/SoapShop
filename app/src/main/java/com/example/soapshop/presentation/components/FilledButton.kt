package com.example.soapshop.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.soapshop.ui.theme.MainTypography

@Composable
fun FilledButton(
    modifier: Modifier = Modifier,
    text: String = "",
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
        ,
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(top = 18.dp,bottom = 15.dp),
        enabled = isEnabled,
        onClick = onClick
    ) {
        Text(
            text = text,
            style = MainTypography.buttonMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}