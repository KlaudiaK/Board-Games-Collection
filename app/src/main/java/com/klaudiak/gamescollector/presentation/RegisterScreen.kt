package com.klaudiak.gamescollector.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.klaudiak.gamescollector.viewmodel.GameViewModel
import com.klaudiak.gamescollector.viewmodel.RegisterScreenEvent

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RegisterScreen(
    navController: NavController,
    gameViewModel: GameViewModel
) {



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
            label = { Text(text = "Username") }
        )
        Button(onClick = {
            gameViewModel
            .onEvent(RegisterScreenEvent.OnRegisterClickedEvent(username.value.text));
            navController.navigate("home")
                         },
            modifier = Modifier
                .padding(top = 8.dp, bottom = 16.dp)
                .align(CenterHorizontally)
                .width(150.dp)
                .height(50.dp),
            colors = ButtonDefaults
                .buttonColors(MaterialTheme.colors.secondary),

            ) {
            Text(text = "Log in".uppercase())
        }
    }

}




