package com.klaudiak.gamescollector.viewmodel


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.klaudiak.gamescollector.data.repository.MainRepository
import com.klaudiak.gamescollector.domain.Game
import com.klaudiak.gamescollector.domain.Username
import com.klaudiak.gamescollector.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    //private val savedStateHandle: SavedStateHandle
) : ViewModel()  {

    var state by mutableStateOf(GamesListScreenState())
        private set
    var stateUsername by mutableStateOf(GamesListScreenState())
        private set

    init {
        getGames("rahdo", "1", "boardgame")
    }

    private val _dataState: MutableLiveData<DataState<List<Game>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<Game>>>
    get () = _dataState


    private val _dataUsernameState: MutableLiveData<DataState<String>> = MutableLiveData()
    val dataUsernameState: LiveData<DataState<String>>
        get () = _dataUsernameState


    private val _dataUsernameApiState: MutableLiveData<DataState<String?>> = MutableLiveData()
    val dataUsernameApiState: LiveData<DataState<String?>>
        get () = _dataUsernameApiState


    fun onEvent(event: GamesListScreenEvent) {
        when(event) {
            GamesListScreenEvent.OnGameClick -> { }

        }
    }

    private fun getGames(username: String, stats: String, type: String){
        viewModelScope.launch {
            mainRepository.getGames(username, stats, type)
                .collect { result ->
                    when(result) {
                        is DataState.Error<*> -> Unit
                        is DataState.Loading -> {Log.i("Game", "Loading")}//{state = state.copy(isRefrefreshing = DataState)}
                        is DataState.Success -> {
                            result.data.let { games ->
                                state = state.copy(gamesList = games, isRefreshing = false)
                                Log.i("Game", games.size.toString())
                            }
                        }
                    }
                }



        }
    }

/*
    fun setUsernameEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetGamesEvents -> {
                    mainRepository.getUserFirstTime("rahdo")
                        .onEach { dataUsernameState ->
                            _dataUsernameState.value = dataUsernameState
                        }
                        .launchIn(viewModelScope)
                }
                else -> {}
            }
        }
    }

 */

    fun setUserFromApi(username:String){/*
        viewModelScope.launch {
            mainRepository.getUserFirstTime("batman")
                .collect { result->
                    when(result) {
                        is DataState.Error<*> -> Unit
                        is DataState.Loading -> {Log.i("Game", "Loading")}//{state = state.copy(isRefrefreshing = DataState)}
                        is DataState.Success -> {
                            result.data.let { username ->
                                state = state.copy(gamesList = games, isRefreshing = false)
                                Log.i("Game", games.size.toString())
                            }
                        }
                    }


            when(mainStateEvent){
                is MainStateEvent.GetUsernameEvent -> {
                    mainRepository.getUsername()
                        .onEach { dataUsernameApiState ->
                            _dataUsernameApiState.value = dataUsernameApiState
                        }
                        .launchIn(viewModelScope)
                }
                else -> {}
            }

                     */

    }
}

sealed class MainStateEvent{
    object GetGamesEvents: MainStateEvent()
    object GetUsernameEvent: MainStateEvent()
    object None: MainStateEvent()
}

data class GamesListScreenState(
    var isRefreshing: Boolean = false,
    val gamesList: List<Game> = mutableStateListOf<Game>()
)

sealed class GamesListScreenEvent {
    class OnTextFieldValueChange(val value: String): GamesListScreenEvent()
    object OnGameClick: GamesListScreenEvent()

}