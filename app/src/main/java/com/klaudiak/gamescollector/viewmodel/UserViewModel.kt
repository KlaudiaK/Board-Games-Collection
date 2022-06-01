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
import com.klaudiak.gamescollector.prefs.Preferences
import com.klaudiak.gamescollector.presentation.Progress
import com.klaudiak.gamescollector.presentation.home.HomeScreenState
import com.klaudiak.gamescollector.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val gameRepository: GamesRepositoryImpl,
    private val preferences: Preferences
) : ViewModel() {


    var state by mutableStateOf(HomeScreenState())

    private val _showSyncDialog = MutableStateFlow(false)
    val showSyncDialog: StateFlow<Boolean> = _showSyncDialog.asStateFlow()

    private val _showProgressIndicator = MutableStateFlow(Progress.BEFORE)
    val showProgressIndicator: StateFlow<Progress> = _showProgressIndicator.asStateFlow()

    private val _showClearAllDialog = MutableStateFlow(false)
    val showClearAllDialog: StateFlow<Boolean> = _showClearAllDialog.asStateFlow()

    fun onOpenSyncDialogClicked() {
        _showSyncDialog.value = true
    }

    fun onSyncDialogConfirm() {
        viewModelScope.launch {
            _showProgressIndicator.value = Progress.DURING
            delay(1800)
            _showProgressIndicator.value = Progress.AFTER
            delay(1800)
            gameRepository.updateLastSyncDate().collect {
                when(it){
                    is DataState.Loading -> {}
                    is DataState.Error<*> -> Unit
                    is DataState.Success -> {
                        _showSyncDialog.value = false
                        }
                    }
                }

            }

        }



    fun onSyncDialogDismiss() {
        _showSyncDialog.value = false
    }


    fun onOpenClearAllDialogClicked() {
        _showClearAllDialog.value = true

    }

    fun onClearAllDialogConfirm() {
        deleteAllData()
        preferences.shouldOpenHome(openHome = false)

    }

    fun onClearAllDialogDismiss() {
        _showClearAllDialog.value = false
    }

    fun deleteAllData(){
        viewModelScope.launch {
            gameRepository.deleteData().collect() { response ->
                when (response) {
                    is DataState.Loading -> {}
                    is DataState.Error<*> -> Unit
                    is DataState.Success -> {
                        response.data.let { deleted ->
                            _showClearAllDialog.value = false
                        }
                    }
                }
            }
        }
    }


    fun getGamesCount() {
        viewModelScope.launch {
            gameRepository.getUserGamesCount().collect { response ->
                when (response) {
                    is DataState.Loading -> {}
                    is DataState.Error<*> -> Unit
                    is DataState.Success -> {
                        response.data.let { count ->
                            state = state.copy(userGamesNum = count)
                        }
                    }
                }
            }
        }

    }

    fun getExtensionsCount() {
        viewModelScope.launch {
            gameRepository.getUserExtensionsCount().collect { response ->
                when (response) {
                    is DataState.Loading -> {}
                    is DataState.Error<*> -> Unit
                    is DataState.Success -> {
                        response.data.let { count ->
                            state = state.copy(userExtensionsNum = count)
                        }
                    }
                }
            }
        }

    }

    fun getUsername() {
        viewModelScope.launch {
            gameRepository.getUsername().collect { response ->
                when (response) {
                    is DataState.Loading -> {}
                    is DataState.Error<*> -> Unit
                    is DataState.Success -> {
                        response.data.let { user ->

                            response.data.let { value -> state = state.copy(username = value) }
                        }
                    }
                }
            }
        }
    }

    fun getLastSyncDate() {
        viewModelScope.launch {
            gameRepository.getLastSyncDate().collect { response ->
                when (response) {
                    is DataState.Loading -> {}
                    is DataState.Error<*> -> Unit
                    is DataState.Success -> {
                        response.data.let { user ->
                            response.data.let { value -> state = state.copy(lastSyncDate = value) }
                        }
                    }
                }
            }
        }
    }


}

