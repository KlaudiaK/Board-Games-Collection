package com.klaudiak.gamescollector.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.klaudiak.gamescollector.prefs.Preferences

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationComponent(preferences: Preferences,
                        navController: NavHostController) {

    val shouldShowHome = preferences.loadShouldOpenHome()
    val startDestination = if(shouldShowHome) Screen.HomeScreen.route else Screen.RegisterScreen.route
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(navController,preferences = preferences)
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(Screen.GameListScreen.route) {
            GameListScreen(navController = navController)
        }
        composable(Screen.ExtensionListScreen.route) {
            ExtensionListScreen(navController = navController)
        }
        composable(Screen.RankingHistoryScreen.route,
        arguments = listOf(navArgument(RANKING_HISTORY_ARGUMENT_KEY){
            type = NavType.StringType
        })) {
            RankingHistoryScreen(
                navController = navController,
               id = it.arguments?.getString(RANKING_HISTORY_ARGUMENT_KEY)

            )

        }
    }
}