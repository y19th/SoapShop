package com.example.catalog.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.components.CrossedText
import com.example.ui.R
import com.example.ui.theme.LightPink
import com.example.ui.theme.MainTypography

@Composable
internal fun ProductBuyButton(
    modifier: Modifier = Modifier,
    priceModel: com.example.domain.models.catalog.PriceModel,
    onClick: () -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = com.example.ui.theme.Pink,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick.invoke() }
            .padding(horizontal = 16.dp, vertical = 17.dp)
            .clip(RoundedCornerShape(8.dp))
            .then(modifier)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = priceModel.priceWithDiscount,
                style = MainTypography.buttonMedium,
                color = com.example.ui.theme.White
            )
            CrossedText(
                title = priceModel.price,
                contentColor = LightPink,
                textStyle = MainTypography.caption
            )
        }
        Text(
            text = stringResource(id = R.string.add_to_cart),
            style = MainTypography.buttonMedium,
            color = com.example.ui.theme.White
        )
    }
}
