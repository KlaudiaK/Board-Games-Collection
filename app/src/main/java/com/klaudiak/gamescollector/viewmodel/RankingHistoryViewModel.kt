package com.klaudiak.gamescollector.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klaudiak.gamescollector.data.repository.GamesRepositoryImpl
import com.klaudiak.gamescollector.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RankingHistoryViewModel @Inject constructor(
    private val gameRepository: GamesRepositoryImpl
) : ViewModel() {

    var state by mutableStateOf(RankingHistoryState())
    fun getGameRankingHistory(id: String) {
        viewModelScope.launch {
            gameRepository.getLastRating(id).collect { response ->
                when (response) {
                    is DataState.Loading -> {}
                    is DataState.Error<*> -> {}
                    is DataState.Success -> {

                        response.data.let {
                            state.rankingLastPos = response.data
                        }
                    }
                }
            }
        }
    }
}

data class RankingHistoryState(
    var rankingLastPos: String = "0"
)