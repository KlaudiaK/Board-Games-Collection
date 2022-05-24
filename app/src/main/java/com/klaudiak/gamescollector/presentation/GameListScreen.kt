package com.klaudiak.gamescollector.presentation
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.klaudiak.gamescollector.viewmodel.MainViewModel

import kotlinx.coroutines.launch
@Composable
fun GameListScreen(
    viewModel: MainViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val state = viewModel.state

    Column(
        modifier = Modifier.fillMaxSize()
    ) {



            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.gamesList.size) { i ->
                    val game = state.gamesList[i]
                    GameLisItem(
                        game = game,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                //viewModel.onEvent(PokemonListScreenEvent.OnPokemonClick)
                                coroutineScope.launch {
                                    Toast.makeText(context,"Clicked Pokemon //todo add pokemon details screen", Toast.LENGTH_LONG)
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