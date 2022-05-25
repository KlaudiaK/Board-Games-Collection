package com.klaudiak.gamescollector.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.klaudiak.gamescollector.viewmodel.UserViewModel


@Composable
fun HomeScreen(
    navController: NavController,
    userViewModel: UserViewModel
){
   // val gamesList = gameViewModel

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 46.dp, bottom = 46.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        val name = userViewModel.getUsername()
        val gamesNum = userViewModel.getGamesCount()
        val extensionsNum = "40"
        Text(text = "Hello $name!", style = MaterialTheme.typography.h3)

        Row(modifier = Modifier.padding(16.dp)){
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                Modifier.padding(end = 8.dp)
            )
            Text(text = "You have $gamesNum games",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.width(200.dp)
            )
            Button(
                onClick = { },
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = MaterialTheme.colors.onPrimary
                )) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    Modifier.padding(end = 8.dp)
                )
                Text("See your games")
            }
        }


        Row (Modifier.padding(16.dp)) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                Modifier.padding(end = 8.dp)
            )
            Text(text = "You have $extensionsNum extensions",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.width(200.dp))
            Button(
                onClick = { },
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = MaterialTheme.colors.onPrimary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    Modifier.padding(end = 8.dp)
                )
                Text("See your extensions")
            }
        }


    }
    // TODO username
    // TODO num of games -> do to table games
    // TODO num of xetensions -> go to ext table
    //TODO last sync date
    // sync button -> go to sync screen
    // delete all -> if delete all dialog

}