package com.valance.petproject.data.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class ItemData(
    @DrawableRes val imageId: Int,
    val title: String,
    val cardColor: Color,
)
