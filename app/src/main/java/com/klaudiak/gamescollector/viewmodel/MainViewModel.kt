package com.boardgamecollector.viewmodel


import androidx.lifecycle.*
import com.boardgamecollector.data.repository.MainRepository
import com.boardgamecollector.domain.Game
import com.boardgamecollector.utils.DataState
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    //private val savedStateHandle: SavedStateHandle
) : ViewModel(), LifecycleObserver  {

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