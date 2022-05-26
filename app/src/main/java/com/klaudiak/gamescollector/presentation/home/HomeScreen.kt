package com.klaudiak.gamescollector.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klaudiak.gamescollector.ui.theme.GamesCollectorTheme
import com.klaudiak.gamescollector.ui.theme.Shapes
import com.klaudiak.gamescollector.viewmodel.GameViewModel
import com.klaudiak.gamescollector.viewmodel.UserViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    navController: NavController,
    userViewModel: UserViewModel = hiltViewModel<UserViewModel>(),
    gameViewModel: GameViewModel = hiltViewModel<GameViewModel>()
){

    userViewModel.getGamesCount()
    userViewModel.getExtensionsCount()
    userViewModel.getUsername()
    userViewModel.getLastSyncDate()
   val state = userViewModel.state

    val showSyncDialogState: Boolean by userViewModel.showSyncDialog.collectAsState()
    val showClearAllDialogState: Boolean by userViewModel.showClearAllDialog.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 46.dp, bottom = 46.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        //val name = userViewModel.username



        Text(text = "Hello ${state.username}!", style = MaterialTheme.typography.h3)


        Spacer(modifier = Modifier.height(30.dp))
        Row(modifier = Modifier.padding(16.dp)){
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                Modifier.padding(end = 8.dp)
            )
            Text(text = "You have ${state.userGamesNum} games",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.width(200.dp)
            )
            Button(
                onClick = {navController.navigate("game_list")},
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

        Spacer(modifier = Modifier.height(30.dp))
        Row (Modifier.padding(16.dp)) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                Modifier.padding(end = 8.dp)
            )
            Text(text = "You have ${state.userExtensionsNum} extensions",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.width(200.dp))
            Button(
                onClick = {navController.navigate("extension_list") },
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

        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        Text(text = "Last synchronization date: ${state.lastSyncDate}!", style = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        OutlinedButton(
            onClick = {userViewModel.onOpenSyncDialogClicked()// userViewModel.synchronizeData()//
            },
            shape = CircleShape,
            elevation = ButtonDefaults.elevation(8.dp),

            colors = ButtonDefaults.textButtonColors(
                backgroundColor = MaterialTheme.colors.onPrimary
            )
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                Modifier.padding(end = 8.dp)
            )
            Text("Synchronize")
        }

        TextButton(
            onClick = {
               userViewModel.onOpenClearAllDialogClicked()
            },


        ) {
            /*
            Icon(
                imageVector = ,
                contentDescription = null,
                Modifier.padding(end = 8.dp)
            )

             */
            Text("Clear data")
        }

        SyncDialog(show = showSyncDialogState,
            onDismiss = userViewModel::onSyncDialogDismiss,
            onConfirm = userViewModel::onSyncDialogConfirm)

        ClearAllDialog(show = showClearAllDialogState,
            navController = navController,
            onDismiss = userViewModel::onClearAllDialogDismiss,
            onConfirm = userViewModel::onClearAllDialogConfirm)


    }
    // TODO username
    // TODO num of games -> do to table games
    // TODO num of xetensions -> go to ext table
    //TODO last sync date
    // sync button -> go to sync screen
    // delete all -> if delete all dialog

}