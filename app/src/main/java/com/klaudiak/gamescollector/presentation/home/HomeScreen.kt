package com.klaudiak.gamescollector.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klaudiak.gamescollector.R
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
    val showProgress: Progress by userViewModel.showProgressIndicator.collectAsState()

    Scaffold(backgroundColor = MaterialTheme.colors.background) {


        Column(
            modifier = Modifier
                .fillMaxWidth()

                .padding(top = 46.dp, bottom = 46.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Card(
                elevation = 10.dp,
                border = BorderStroke(1.dp, MaterialTheme.colors.onSurface),
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize()
                    .padding(start = 16.dp, end = 16.dp)
                    .clip(RoundedCornerShape(20.dp)),
                backgroundColor = MaterialTheme.colors.primary,

                ) {
                Text(
                    text = "Hello ${state.username}!",
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 16.dp),
                    color = colors.onSecondary
                )
            }



            Spacer(modifier = Modifier.height(30.dp))

            Card(
                elevation = 10.dp,
                border = BorderStroke(1.dp, MaterialTheme.colors.onSurface),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .clip(RoundedCornerShape(20.dp))

            ) {
                Row(modifier = Modifier.padding(16.dp)) {

                    Text(
                        text = "${state.userGamesNum} ",
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 30.dp)
                            .align(Alignment.CenterVertically)
                            .width(80.dp),

                        color = colors.onSecondary
                    )
                    Text(
                        text = "GAMES",
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .align(Alignment.CenterVertically)
                            .width(170.dp),
                        color = colors.onSecondary
                    )
                    IconButton(
                        onClick = { navController.navigate(Screen.GameListScreen.route) },

                        ) {
                        Icon(
                            painter = painterResource(id = R.drawable.right),
                            contentDescription = null,
                            tint = MaterialTheme.colors.onSecondary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Card(
                elevation = 10.dp,
                border = BorderStroke(1.dp, MaterialTheme.colors.onSurface),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .clip(RoundedCornerShape(20.dp))

            ) {
                Row(Modifier.padding(16.dp)) {

                    Text(
                        text = "${state.userExtensionsNum}",
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 30.dp)
                            .align(Alignment.CenterVertically)
                            .width(80.dp),
                        color = colors.onSecondary
                    )
                    Text(
                        text = "EXTENSIONS",
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .align(Alignment.CenterVertically)
                            .width(170.dp),
                        color = colors.onSecondary
                    )



                    IconButton(
                        onClick = { navController.navigate(Screen.ExtensionListScreen.route) },
                        modifier = Modifier.padding(end = 10.dp)

                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.right),
                            contentDescription = null,
                            tint = MaterialTheme.colors.onSecondary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.padding(vertical = 16.dp))
            Text(text = "Last synchronization date:", style = MaterialTheme.typography.h6, color = MaterialTheme.colors.onSecondary)
            Text(text = state.lastSyncDate, style = MaterialTheme.typography.h5, color = MaterialTheme.colors.onSecondary)
            Spacer(modifier = Modifier.padding(vertical = 16.dp))
            OutlinedButton(
                onClick = {
                    userViewModel.onOpenSyncDialogClicked()
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

                Text(text = "Synchronize", fontSize = 18.sp)
            }

            TextButton(
                onClick = {
                    userViewModel.onOpenClearAllDialogClicked()
                },


                ) {

                Text(text = "Clear data", fontSize = 18.sp, color = colors.onPrimary)
            }

            SyncDialog(
                lastSyncDate = userViewModel.state.lastSyncDate,
                show = showSyncDialogState,
                showProgress = showProgress,
                onDismiss = userViewModel::onSyncDialogDismiss,
                onConfirm = userViewModel::onSyncDialogConfirm
            )

            ClearAllDialog(

                show = showClearAllDialogState,
                navController = navController,
                onDismiss = userViewModel::onClearAllDialogDismiss,
                onConfirm = userViewModel::onClearAllDialogConfirm
            )

        }
    }


}