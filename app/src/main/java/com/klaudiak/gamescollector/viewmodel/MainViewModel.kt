package com.klaudiak.gamescollector.viewmodel


import androidx.lifecycle.*
import com.klaudiak.gamescollector.data.repository.MainRepository
import com.klaudiak.gamescollector.domain.Game
import com.klaudiak.gamescollector.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    //private val savedStateHandle: SavedStateHandle
) : ViewModel()  {

    private val _dataState: MutableLiveData<DataState<List<Game>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<Game>>>
    get () = _dataState
    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetGamesEvents -> {
                    mainRepository.getGames("rahdo", "1", "boardgame")
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                else -> {}
            }
        }
    }
}

sealed class MainStateEvent{
    object GetGamesEvents: MainStateEvent()
    object None: MainStateEvent()
}