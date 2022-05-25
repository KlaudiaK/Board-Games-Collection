package com.klaudiak.gamescollector.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klaudiak.gamescollector.data.repository.GamesRepositoryImpl
import com.klaudiak.gamescollector.domain.Game
import com.klaudiak.gamescollector.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameRepository: GamesRepositoryImpl
    //private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    var games by mutableStateOf(emptyList<Game>())
    var game by mutableStateOf(Game("", "", "", "", ""))

    fun getGames(username: String, stats: String, type: String){
        viewModelScope.launch {
            gameRepository.getGames(username,stats, type).collect{ response ->
               when(response) {
                   is DataState.Loading -> Unit
                   is DataState.Error<*> -> Unit
                   is DataState.Success -> {
                       response.data.let { gamesList ->  games = gamesList}
                   }
               }
            }
        }
    }
    //TODO count games, count extensions, last sync

    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: RegisterScreenEvent){
        when(event){
           is RegisterScreenEvent.OnRegisterClickedEvent -> {
               Log.i("name", event.username)
               viewModelScope.launch {
                   Log.i("name", "userinserted!")
                   gameRepository.saveUser(event.username)

               }
               Log.i("name", event.username)
           }
        }
    }
}

sealed class RegisterScreenEvent{
    data class OnRegisterClickedEvent(val username: String) : RegisterScreenEvent()
}
