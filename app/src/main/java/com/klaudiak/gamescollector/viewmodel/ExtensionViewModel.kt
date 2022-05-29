package com.klaudiak.gamescollector.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klaudiak.gamescollector.data.repository.GamesRepositoryImpl
import com.klaudiak.gamescollector.domain.Extension
import com.klaudiak.gamescollector.presentation.SortOpt
import com.klaudiak.gamescollector.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ExtensionViewModel @Inject constructor(
    private val gameRepository: GamesRepositoryImpl
    //private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(ExtensionListScreenState())

    //  var games by mutableStateOf(emptyList<Game>())
    //var game by mutableStateOf(Game("", "", "", "", ""))

    fun getExtensions() {
        viewModelScope.launch {
            gameRepository.getExtensions().collect { response ->
                when (response) {
                    is DataState.Loading -> Unit
                    is DataState.Error<*> -> Unit
                    is DataState.Success -> {
                        response.data.let { extensionList -> state.extensionList = extensionList }
                      //  Log.i("GAMES", state.extensionList.size.toString())
                    }
                }
            }
        }
    }


    fun getExtensions(bodyContent: MutableState<SortOpt>){
        viewModelScope.launch {
            when(bodyContent.value){
                SortOpt.UNSORTED -> gameRepository.getExtensions().collect{ response ->
                    when(response) {
                        is DataState.Success -> {
                            //  Log.i("GAMES", response.data.toString())
                            response.data.let { extensionList ->  state.extensionList = extensionList}
                            // Log.i("GAMES", state.gamesList.size.toString())
                        }
                    }
                }
                SortOpt.RELEASE_YEAR -> gameRepository.getExtensionsSortedByReleaseYear().collect{ response ->
                    when(response) {
                        is DataState.Success -> {

                            response.data.let { extensionList ->  state.extensionList = extensionList}
                            // Log.i("GAMES", state.gamesList.size.toString())
                        }
                    }
                }
                SortOpt.TITLE -> gameRepository.getExtensionsSortedByTitle().collect{ response ->
                    when(response) {
                        is DataState.Success -> {
                            response.data.let { extensionList ->  state.extensionList = extensionList}
                        }
                    }
                }

            }

        }
    }

}

data class ExtensionListScreenState(
    var isRefreshing: Boolean = false,
    var extensionList: List<Extension> = mutableStateListOf<Extension>()
)

