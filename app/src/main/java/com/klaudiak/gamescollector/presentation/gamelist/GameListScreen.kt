package com.klaudiak.gamescollector.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klaudiak.gamescollector.viewmodel.GameViewModel

@Composable
fun GameListScreen(
    navController:NavController,
    gamesViewModel: GameViewModel = hiltViewModel<GameViewModel>()
) {

    val state = gamesViewModel.state

    val bodyContent = remember { mutableStateOf(SortOpt.UNSORTED) }

    gamesViewModel.getGames(bodyContent)
    Scaffold(
        topBar =

        {
            TopAppBar(
                backgroundColor = backgroundColor,
                title = {
                    Text(
                        text = "Title",
                        style = MaterialTheme.typography.h6,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {navController.navigate(Screen.HomeScreen.route)}) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
                actions = {
                    SortMenu(ifGames = true,bodyContent = bodyContent)

                }

            )
        },

        ) {


            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                TopAppBar {
                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween){
                        Text(text = "Id", color = Color.Black, modifier = Modifier.padding(16.dp))
                        Text(text = "Logo", color = Color.Black, modifier = Modifier.padding(16.dp))
                        Text(text = "Title",  color = Color.Black, modifier = Modifier.padding(16.dp))
                        Text(text = "Rating",  color = Color.Black, modifier = Modifier.padding(16.dp))

                    }

                }


                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {

                    items(state.gamesList.size) { i ->
                        val game = state.gamesList[i]
                        GameListItem(
                            game = game,
                            navController = navController,
                            modifier = Modifier
                                .fillMaxWidth()

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


