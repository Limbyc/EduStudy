package com.valance.petproject.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valance.petproject.R

@Composable
fun ButtonDate(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(modifier = modifier
        .clip(RoundedCornerShape(8.dp))
        .background(color = com.valance.petproject.presentation.ui.theme.ButtonDate)
        .padding(top = 8.dp, bottom = 8.dp , start = 16.dp, end = 16.dp)
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) {
            onClick()
        }
    ){
        CurrentDateText(text = stringResource(R.string.today))
    }
}


@Preview
@Composable
fun ButtonDatePreview(){
    ButtonDate(onClick = { })
}