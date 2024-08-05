package com.valance.petproject.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.valance.petproject.ui.BottomBarScreen
import com.valance.petproject.ui.screen.MainScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    )
    {
        composable(BottomBarScreen.Home.route) {
            MainScreen()
        }

        composable(BottomBarScreen.Detail.route) {
            MainScreen()
        }
    }
}
