package com.valance.petproject.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainText(
    text: String,
    modifier: Modifier = Modifier,
    ){
    Text(
        modifier = modifier,
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = Color.Black,
    )
}

@Composable
fun SecondaryText(
    modifier: Modifier = Modifier,
    text: String,
    ){
    Text(
        modifier = modifier,
        text = text,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        color = Color.Gray,
    )
}