package com.klaudiak.gamescollector.prefs

import android.content.SharedPreferences

class PreferencesImpl(private val sharedPreferences: SharedPreferences): Preferences{
    override fun shouldOpenHome(openHome: Boolean) {
        sharedPreferences
            .edit()
            .putBoolean(Preferences.KEY_SHOULD_OPEN_HOME, openHome)
            .apply()

    }

    override fun loadShouldOpenHome(): Boolean {
        return sharedPreferences.getBoolean(Preferences.KEY_SHOULD_OPEN_HOME, true)
    }
}