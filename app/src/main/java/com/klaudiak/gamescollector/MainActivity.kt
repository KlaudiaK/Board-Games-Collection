package com.klaudiak.gamescollector


import android.os.Build
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Scaffold
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.klaudiak.gamescollector.data.local.AppDatabase
import com.klaudiak.gamescollector.data.remote.NetworkService
import com.klaudiak.gamescollector.presentation.NavigationComponent
import com.klaudiak.gamescollector.ui.theme.GamesCollectorTheme
import com.klaudiak.gamescollector.viewmodel.GameViewModel
import com.klaudiak.gamescollector.viewmodel.MainViewModel
import com.klaudiak.gamescollector.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.hilt.navigation.compose.hiltViewModel
import com.klaudiak.gamescollector.presentation.HomeScreen
import com.klaudiak.gamescollector.presentation.RegisterScreen

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val gameViewModel: GameViewModel by viewModels()
    private val viewModel: MainViewModel by viewModels()
    private val userViewModel:UserViewModel by viewModels()
    @Inject
    lateinit var networkService: NetworkService

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
       // deleteDatabase("")
        AppDatabase.getDatabase(this)


        setContent {
            val navController = rememberNavController()

            GamesCollectorTheme {
                Scaffold {
                    NavigationComponent(
                        gameViewModel,
                        userViewModel,
                        navController)
                }
            }
        }


    }


}