package com.klaudiak.gamescollector.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klaudiak.gamescollector.viewmodel.GameViewModel
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.klaudiak.gamescollector.R
import com.klaudiak.gamescollector.viewmodel.GamesListScreenEvent

import kotlinx.coroutines.launch
@Composable
fun GameListScreen(
    navController:NavController,
    gamesViewModel: GameViewModel = hiltViewModel<GameViewModel>()
) {

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    gamesViewModel.getGames()
    val state = gamesViewModel.state
    val bodyContent = remember { mutableStateOf(SortOpt.UNSORTED) }


    Scaffold(
        topBar =

        {
            TopAppBar(
                backgroundColor = backgroundColor,
                title = {
                    Text(
                        text = "Title",
                        style = MaterialTheme.typography.h6,
                        // color = contentColor
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            //  tint = contentColor
                        )
                    }
                },
                actions = {
                    SortMenu(bodyContent = bodyContent)

                    IconButton(onClick = {  }) {
                        Icon(
                            painter = painterResource(id = R.drawable.sort),
                            contentDescription = "Share",
                            tint = Color.White
                        )
                    }

                }

            )
        },

        ) {


        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopAppBar {
                Row(){
                    Text(text = "No.")

                }

            }


            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {

                items(state.gamesList.size) { i ->
                    val game = state.gamesList[i]
                    GameListItem(
                        game = game,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                gamesViewModel.onGamesListEvent(GamesListScreenEvent.OnGameClick)
                                coroutineScope.launch {
                                    Toast.makeText(
                                        context,
                                        "Clicked game ",
                                        //TODO add game history screen
                                        Toast.LENGTH_LONG
                                    )
                                }
                            }
                            .padding(16.dp)
                    )
                    if(i < state.gamesList.size) {
                        Divider(modifier = Modifier.padding(
                            horizontal = 16.dp
                        ))
                    }
                }
            }
        }
}




}

