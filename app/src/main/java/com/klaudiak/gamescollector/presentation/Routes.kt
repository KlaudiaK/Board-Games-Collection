package com.klaudiak.gamescollector.presentation

sealed class Routes(val routes: String) {
    object ConfigScreen: Routes(routes = "config_screen")
    object UserScreen: Routes(routes = "user_screen")
    object ElementScreen: Routes(routes = "element_screen")
    // object TestScreen: Screen("test_screen")
    //object SyncScreen: Routes("sync_screen")
    object RankingHistoryScreen: Routes(routes = "ranking_history_screen")
}