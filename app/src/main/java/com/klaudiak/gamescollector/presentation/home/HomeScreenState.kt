package com.klaudiak.gamescollector.presentation.home

data class HomeScreenState(
    var username: String = "User",
    var lastSyncDate : String = "",
    var userGamesNum: Int= 0,
    var userExtensionsNum: Int = 0,
    var isSyncDialogOpen: Boolean = false,
    var isClearAllDialogOpen: Boolean = false
)