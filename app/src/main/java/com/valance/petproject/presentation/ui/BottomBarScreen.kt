package com.valance.petproject.presentation.ui

import androidx.annotation.DrawableRes
import com.valance.petproject.R

sealed class BottomBarScreen(
    val route: String,
    val label: String,
    @DrawableRes val icon: Int,
) {

    data object Home : BottomBarScreen(
        route = "main_screen",
        label = "Home",
        icon = R.drawable.home,
    )

    data object Detail : BottomBarScreen(
        route = "detail_screen",
        label = "Detail",
        icon = R.drawable.notes,
    )
}
