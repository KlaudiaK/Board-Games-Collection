package com.klaudiak.gamescollector.presentation
const val RANKING_HISTORY_ARGUMENT_KEY = "id"

sealed class Screen(val route: String) {
    object RegisterScreen: Screen(route = "register_screen")
    object HomeScreen: Screen(route = "home_screen")
    object GameListScreen: Screen(route = "game_list_screen")
    object ExtensionListScreen: Screen(route = "extension_list_screen")

    object RankingHistoryScreen: Screen(route = "ranking_history/{$RANKING_HISTORY_ARGUMENT_KEY}"){
        fun passId(id: String):String {
            return "ranking_history/$id"
        }
    }
}