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
import com.klaudiak.gamescollector.presentation.HomeScreenState

import com.klaudiak.gamescollector.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val gameRepository: GamesRepositoryImpl
    //private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
    }

    var state by mutableStateOf(HomeScreenState())

    var gamesCount by mutableStateOf(0)
    var game by mutableStateOf(Game("", "", "", "", ""))

    private val _showSyncDialog = MutableStateFlow(false)
    val showSyncDialog: StateFlow<Boolean> = _showSyncDialog.asStateFlow()
    private val _showClearAllDialog = MutableStateFlow(false)
    val showClearAllDialog: StateFlow<Boolean> = _showClearAllDialog.asStateFlow()

    fun onOpenSyncDialogClicked() {
        _showSyncDialog.value = true
    }

    fun onSyncDialogConfirm() {
        _showSyncDialog.value = false
        // Continue with executing the confirmed action
    }

    fun onSyncDialogDismiss() {
        _showSyncDialog.value = false
    }


    fun onOpenClearAllDialogClicked() {
        _showClearAllDialog.value = true
    }

    fun onClearAllDialogConfirm() {
        _showClearAllDialog.value = false
        // Continue with executing the confirmed action
        viewModelScope.launch {
            gameRepository.deleteData().collect() { response ->
                when (response) {
                    is DataState.Loading -> {Log.i("DELETED", "DELETED")}
                    is DataState.Error<*> -> Unit
                    is DataState.Success -> {
                        response.data.let { deleted ->
                            Log.i("DELETED", deleted.toString())
                        }
                    }
                }
            }
        }
    }

    fun onClearAllDialogDismiss() {
        _showClearAllDialog.value = false
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
                            //state.username = user
                            //   Log.i("Repo view model", state.toString())}
                            val res = response.data
                            Log.i("Repo view model", res)
                            //  state.value.username = user
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
                            //state.username = user
                            //   Log.i("Repo view model", state.toString())}
                            val res = response.data
                            //  state.value.username = user
                            response.data.let { value -> state = state.copy(lastSyncDate = value) }
                        }
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun synchronizeData(){
      //  val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:MM:SS")
      //  val currentDT: Date = simpleDateFormat.format(Date())

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val currentTime = current.format(formatter)

        val pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        Log.i("DATE", current.toString())
        //c.add(Calendar.DATE, 1)
        //Log.i("DATE",  LocalDateTime.parse(state.lastSyncDate, pattern).plusSeconds(80).isAfter(current).toString())
        Log.i("DATE", current.isAfter(LocalDateTime.parse(state.lastSyncDate, pattern).plusDays(1)).toString())
        ///val simpleDateFormat= SimpleDateFormat("dd-MM-yyyy HH:MM:SS")
        //val currentDT: Date = simpleDateFormat.format(Date())
      //  if(simpleDateFormat.format(state.lastSyncDate).plus()   )
    }


    fun saveUser(username: String) {
        viewModelScope.launch {
            Log.i("name", username)
            gameRepository.saveUser(username).collect { response ->
                when (response) {
                    is DataState.Loading -> Unit
                    is DataState.Error<*> -> Unit
                    is DataState.Success -> {

                        response.data.let {
                            state = state.copy(username = it)

                        }
                    }
                }

            }
        }
    }




        fun onEvent(event: HomeScreenEvent) {
            when (event) {
                is HomeScreenEvent.OnGoToGameListClickedEvent -> {

                }

                is HomeScreenEvent.OnClearDataClickedEvent -> {
                    viewModelScope.launch {
                        gameRepository.deleteData()
                    }

                }

                is HomeScreenEvent.OnSynchronizeClickedEvent -> {
                    //state.isSyncDialogOpen = true
                    onOpenSyncDialogClicked()
                }
            }
        }

}


sealed class HomeScreenEvent{
    object OnGoToGameListClickedEvent : HomeScreenEvent()
    object OnGoToExtensionListClickedEvent: HomeScreenEvent()
    object OnSynchronizeClickedEvent : HomeScreenEvent()
    object OnClearDataClickedEvent : HomeScreenEvent()
}
