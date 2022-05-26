package com.klaudiak.gamescollector.prefs

interface Preferences {
    fun shouldOpenHome(openHome: Boolean)
    fun loadShouldOpenHome(): Boolean

    companion object {
        const val KEY_SHOULD_OPEN_HOME = "should_open"
    }
}