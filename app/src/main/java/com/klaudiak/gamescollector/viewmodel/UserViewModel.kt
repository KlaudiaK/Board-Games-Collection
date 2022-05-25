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
class UserViewModel @Inject constructor(
    private val gameRepository: GamesRepositoryImpl
    //private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    init{
        getGamesCount()
        getUsername()
    }

    var gamesCount : Int = 0
    var game by mutableStateOf(Game("", "", "", "", ""))
    var username: String = ""
    fun getGamesCount(){
        viewModelScope.launch {
            gameRepository.getUserGamesCount().collect{ response ->
                when(response) {
                    is DataState.Loading -> Unit
                    is DataState.Error<*> -> Unit
                    is DataState.Success -> {
                        response.data.let { count ->  gamesCount = count}
                    }
                }
            }
        }
    }

    fun getUsername(){
        viewModelScope.launch {
            gameRepository.getUsername().collect{ response ->
                when(response) {
                    is DataState.Loading -> {}
                    is DataState.Error<*> -> Unit
                    is DataState.Success -> {
                        response.data.let { value -> username = value}
                    }
                }
            }
        }
    }
    //TODO count games, count extensions, last sync


    fun onEvent(event: HomeScreenEvent){
        when(event){
            is HomeScreenEvent.OnGoToGameListClickedEvent -> {

            }
        }
    }
}

sealed class HomeScreenEvent{
    object OnGoToGameListClickedEvent : HomeScreenEvent()
    object OnGoToExtensionListClickedEvent: HomeScreenEvent()
    object OnSynchronizeClickedEvent : HomeScreenEvent()
}
