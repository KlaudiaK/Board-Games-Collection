package com.klaudiak.gamescollector.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klaudiak.gamescollector.data.repository.GamesRepositoryImpl
import com.klaudiak.gamescollector.domain.Game
import com.klaudiak.gamescollector.presentation.SortOpt
import com.klaudiak.gamescollector.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameRepository: GamesRepositoryImpl
) : ViewModel() {
    var state by mutableStateOf(GamesListScreenState())
    var registerState by mutableStateOf(RegisterScreenState())

    fun getGames(bodyContent: MutableState<SortOpt>){
        viewModelScope.launch {
            when(bodyContent.value){
                SortOpt.UNSORTED -> gameRepository.getGames().collect{ response ->
                    when(response) {
                        is DataState.Success -> {
                            response.data.let { gamesList ->  state.gamesList = gamesList}
                        }
                    }
                }
                SortOpt.RELEASE_YEAR -> gameRepository.getGamesSortedByReleaseYear().collect{ response ->
                    when(response) {
                        is DataState.Success -> {
                            response.data.let { gamesList ->  state.gamesList = gamesList}

                        }
                    }
                }
                SortOpt.TITLE -> gameRepository.getGamesSortedByTitle().collect{ response ->
                    when(response) {
                        is DataState.Success -> {
                            response.data.let { gamesList ->  state.gamesList = gamesList}
                        }
                    }
                }
                SortOpt.RATING -> gameRepository.getGamesSortedByRating().collect{ response ->
                    when(response) {
                        is DataState.Success -> {
                            response.data.let { gamesList ->  state.gamesList = gamesList}

                        }
                    }
                }

            }

        }
    }


    fun saveUser(username: String) {
        viewModelScope.launch {
            gameRepository.saveUser(username).collect { response ->
                when (response) {
                    is DataState.Loading -> {registerState.isLoading = true}
                    is DataState.Error<*> -> { response.let {
                        registerState.incorrectUsername = true }}
                    is DataState.Success -> {

                        response.data.let {
                            registerState.incorrectUsername = false
                            registerState.isLoading = false

                        }
                    }
                }
            }
        }
    }



    fun syncData(username: String) {

        viewModelScope.launch {
            gameRepository.getGamesSynchronize(username, "1", "boardgame").collect { response ->
                when (response) {
                    is DataState.Loading -> Unit
                    is DataState.Error<*> -> Unit
                    is DataState.Success -> {
                        response.data.let {

                        }
                    }
                }
            }


            gameRepository.getExtensionsSynchronize(username, "boardgameexpansion")
                .collect { response ->
                    when (response) {
                        is DataState.Loading -> Unit
                        is DataState.Error<*> -> Unit
                        is DataState.Success -> {
                            response.data.let {

                            }
                        }
                    }
                }
        }
    }


}

data class RegisterScreenState(
    var incorrectUsername: Boolean = true,
    var isLoading : Boolean = true
)

data class GamesListScreenState(
    var isRefreshing: Boolean = false,
    var gamesList: List<Game> = mutableStateListOf<Game>()
)

