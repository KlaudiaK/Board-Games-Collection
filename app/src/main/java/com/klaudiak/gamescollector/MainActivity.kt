package com.klaudiak.gamescollector

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.boardgamecollector.di.NetworkModule
import com.boardgamecollector.domain.Game
import com.boardgamecollector.utils.DataState
import com.boardgamecollector.viewmodel.MainViewModel
import com.example.rickandmorty.data.remote.NetworkService
import com.klaudiak.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
private lateinit var text: TextView
    private val TAG: String = "AppDebug"



    //lateinit var viewModel: MainViewModel

   // private val viewModel: MainViewModel by viewModels()
     @Inject
     lateinit var networkService: NetworkService


   // private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.tag("INFO").i("START")
        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val viewModel: MainViewModel by viewModels()
        subscribeObservers(viewModel)
        // viewModel.setStateEvent(MainStateEvent.GetGamesEvents)
        //text = findViewById<TextView>(R.id.textField)
        //networkService = NetworkModule.provideRetrofitService()

    }

    private fun subscribeObservers(viewModel: MainViewModel){
        viewModel.dataState.observe(this, Observer { dataState ->
            when(dataState){
                is DataState.Success<List<Game>> -> {
                    displayProgressBar(false)
                    appendBlogTitles(dataState.data)
                }
                is DataState.Error<*> -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayError(message: String?){

       // if(message != null) text.text = message else text.text = "Unknown error."
    }

    private fun appendBlogTitles(games: List<Game>){
        val sb = StringBuilder()
        for(game in games){
            sb.append(game.id + "\n")
        }
        text.text = sb.toString()
    }

    private fun displayProgressBar(isDisplayed: Boolean){
       // progress_bar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

}