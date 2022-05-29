package com.klaudiak.gamescollector.presentation

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klaudiak.gamescollector.prefs.Preferences
import com.klaudiak.gamescollector.viewmodel.GameViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RegisterScreen(
    navController: NavController,
    gameViewModel: GameViewModel = hiltViewModel<GameViewModel>(),
    preferences: Preferences
) {

    val coroutineScope = rememberCoroutineScope()

    preferences.shouldOpenHome(openHome = false)
    val context = LocalContext.current
    val registerState = gameViewModel.registerState
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)){
        val username = remember { mutableStateOf(TextFieldValue()) }
        Text(
            text =  "Keep track on your board games collection!",
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3,

            )
        OutlinedTextField(
            value = username.value,
            modifier = Modifier
                .padding(top = 36.dp, bottom = 8.dp)
                .align(CenterHorizontally),
            onValueChange = { username.value = it},
            label = { Text(text = "Username") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colors.onSecondary,
                unfocusedBorderColor =  colors.onSecondary,
                focusedLabelColor = colors.onSecondary,
                unfocusedLabelColor = colors.onSecondary
            )

        )
        Button(onClick = {

            if(username.value.text.isBlank() ||username.value.text.isDigitsOnly()){
                Toast.makeText(context, "Incorrect username", Toast.LENGTH_LONG ).show()
            }
            else{

                gameViewModel.saveUser(username.value.text)
                coroutineScope.launch {
                    Toast.makeText(context, "Wait", Toast.LENGTH_LONG ).show()
                        delay(5000)
                        if(registerState.incorrectUsername){
                            Toast.makeText(context, "This user doesn't exist", Toast.LENGTH_LONG ).show()
                        }
                        else{
                            gameViewModel.syncData(username.value.text)
                            navController.navigate(Screen.HomeScreen.route)
                            preferences.shouldOpenHome(openHome = false)
                        }
                }

            } },
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
                .align(CenterHorizontally)
                .width(150.dp)
                .height(50.dp),
            colors = ButtonDefaults
                .buttonColors(backgroundColor = colors.secondary),

            ) {
            Text(text = "Log in".uppercase())
        }
    }

}




