package com.klaudiak.gamescollector.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
    var state by mutableStateOf(GamesListScreenState())

  //  var games by mutableStateOf(emptyList<Game>())
    //var game by mutableStateOf(Game("", "", "", "", ""))

    fun getGames(){
        viewModelScope.launch {
            gameRepository.getGames().collect{ response ->
               when(response) {
                   is DataState.Loading -> Unit
                   is DataState.Error<*> -> Unit
                   is DataState.Success -> {
                       Log.i("GAMES", response.data.toString())
                       response.data.let { gamesList ->  state.gamesList = gamesList}
                       Log.i("GAMES", state.gamesList.size.toString())
                   }
               }
            }
        }
    }


    fun saveUser(username: String) {
        viewModelScope.launch {
            //   Log.i("name", username)
            gameRepository.saveUser(username).collect { response ->
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
            //  gameRepository.getGamesSynchronize(username, )
        }
    }

    //TODO count games, count extensions, last sync

    //@RequiresApi(Build.VERSION_CODES.O)
    fun onRegisterEvent(event: RegisterScreenEvent){
        when(event){
           is RegisterScreenEvent.OnRegisterClickedEvent -> {
              // Log.i("name", event.username)
               viewModelScope.launch {
                   Log.i("name", event.username)

                   gameRepository.saveUser("Rahdo")
                   Log.i("after", "userinserted!")
                    gameRepository.getGamesSynchronize(event.username, "1", "boardgame")
               }
           //    Log.i("name", event.username)
           }
        }
    }


    fun onGamesListEvent(event: GamesListScreenEvent){
        when(event){
            is GamesListScreenEvent.OnGameClick -> {



            }
        }
    }
}

sealed class RegisterScreenEvent{
    data class OnRegisterClickedEvent(val username: String) : RegisterScreenEvent()
}
data class GamesListScreenState(
    var isRefreshing: Boolean = false,
    var gamesList: List<Game> = mutableStateListOf<Game>()
)

sealed class GamesListScreenEvent {
    object OnGameClick: GamesListScreenEvent()

}

/*
data class SortOptions {
    val ID,
    val TITLE,
    val RATING
}

 */