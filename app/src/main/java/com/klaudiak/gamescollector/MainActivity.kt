package com.klaudiak.gamescollector


import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import com.klaudiak.gamescollector.data.local.AppDatabase
import com.klaudiak.gamescollector.data.remote.NetworkService
import com.klaudiak.gamescollector.prefs.Preferences
import com.klaudiak.gamescollector.presentation.NavigationComponent
import com.klaudiak.gamescollector.ui.theme.GamesCollectorTheme
import com.klaudiak.gamescollector.viewmodel.GameViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val gameViewModel: GameViewModel by viewModels()


    @Inject
    lateinit var preferences: Preferences

    @Inject
    lateinit var networkService: NetworkService

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val shouldShowOnboarding = preferences.loadShouldOpenHome()
        AppDatabase.getDatabase(this)

        supportActionBar?.hide()
        setContent {
            val navController = rememberNavController()

            GamesCollectorTheme {
                Scaffold {
                    NavigationComponent(
                        preferences,
                        navController)
                }
            }
        }
    }
}