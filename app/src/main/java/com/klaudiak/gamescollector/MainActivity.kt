package com.klaudiak.gamescollector


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.Observer
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.klaudiak.gamescollector.data.remote.NetworkService
import com.klaudiak.gamescollector.domain.Game
import com.klaudiak.gamescollector.presentation.GameListScreen
import com.klaudiak.gamescollector.ui.theme.GamesCollectorTheme
import com.klaudiak.gamescollector.utils.DataState
import com.klaudiak.gamescollector.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var networkService: NetworkService

    private lateinit var text: TextView

    lateinit var pb: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        subscribeObservers(viewModel)
       // viewModel.setUserFromApi(MainStateEvent.GetUsernameEvent)
      //  viewModel.setStateEvent(MainStateEvent.GetGamesEvents)
        //viewModel.setUsernameEvent(MainStateEvent.GetGamesEvents)
       // pb = findViewById<View>(R.id.progress_bar) as ProgressBar
       // text = findViewById<TextView>(R.id.textField) as TextView


      //  val doc = Jsoup.connect("https://boardgamegeek.com/xmlapi2/username=rahdo").get()
        // val allinfo = doc.getElementById("item")

        setContent {
            GamesCollectorTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "list_screen"
                    ) {
                        composable("list_screen") {
                            GameListScreen(viewModel)
                        }
                    }
                }
            }
        }

    }

    private fun subscribeObservers(viewModel: MainViewModel){

        viewModel.dataUsernameApiState.observe(this, Observer { dataUsernameState ->
            when(dataUsernameState){
                is DataState.Success<String?> -> {
                    displayProgressBar(false)
                    dataUsernameState.data?.let { setUserTitle(it) }
                    Log.i("info", (dataUsernameState.data == null).toString())
                    Log.i("INFO", "success")
                }
                is DataState.Error<*> -> {
                    displayProgressBar(false)
                    displayError(dataUsernameState.exception.message)
                    Log.i("INFO", "error user")
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                    Log.i("INFO", "loading user")
                }
            } })

        viewModel.dataUsernameState.observe(this, Observer { dataUsernameState ->
            when(dataUsernameState){
                is DataState.Success<String> -> {
                    displayProgressBar(false)
                    setUserTitle(dataUsernameState.data)
                    Log.i("INFO", "success")
                }
                is DataState.Error<*> -> {
                    displayProgressBar(false)
                    displayError(dataUsernameState.exception.message)
                    Log.i("INFO", "error user")
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                    Log.i("INFO", "loading user")
                }
            } })
        viewModel.dataState.observe(this, Observer { dataState ->
            when(dataState){
                is DataState.Success<List<Game>> -> {
                    displayProgressBar(false)
                    appendBlogTitles(dataState.data)

                }
                is DataState.Error<*> -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                    Log.i("INFO", "error")
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                    Log.i("INFO", "loading")
                }
            }
        })


    }

    private fun displayError(message: String?){

        if(message != null) text.text = message else text.text = "Unknown error."
    }

    private fun appendBlogTitles(games: List<Game>){
        val sb = StringBuilder()
        for(game in games){
            sb.append(game.id + "\n")
        }
        text.text = sb.toString()
    }

    private fun setUserTitle(username: String){
        text.text = username
    }

    private fun displayProgressBar(isDisplayed: Boolean){
      pb.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

}