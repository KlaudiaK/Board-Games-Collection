package com.klaudiak.gamescollector.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klaudiak.gamescollector.data.repository.GamesRepositoryImpl
import com.klaudiak.gamescollector.domain.Extension
import com.klaudiak.gamescollector.domain.Game
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

}

data class ExtensionListScreenState(
    var isRefreshing: Boolean = false,
    var extensionList: List<Extension> = mutableStateListOf<Extension>()
)

