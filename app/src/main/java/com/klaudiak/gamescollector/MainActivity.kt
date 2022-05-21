package com.klaudiak.gamescollector


import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.klaudiak.gamescollector.data.remote.NetworkService
import com.klaudiak.gamescollector.domain.Game
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


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObservers(viewModel)
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