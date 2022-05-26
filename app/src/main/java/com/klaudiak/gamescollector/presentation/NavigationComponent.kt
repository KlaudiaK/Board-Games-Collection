package com.klaudiak.gamescollector.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.klaudiak.gamescollector.viewmodel.GameViewModel
import com.klaudiak.gamescollector.viewmodel.UserViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationComponent(viewModel: GameViewModel,
                        navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "register"
    ) {
        composable("register") {
            RegisterScreen(navController)
        }
        composable("home") {
            //GamesList()
            HomeScreen(navController)
        }
        composable("game_list") {
            //GamesList()
            GameListScreen(navController = navController)
        }
        composable("extension_list") {
            //GamesList()
        }
        composable("ranking_history") {
            //GamesList()
        }
    }
}