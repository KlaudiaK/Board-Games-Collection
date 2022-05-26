package com.klaudiak.gamescollector.presentation

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klaudiak.gamescollector.viewmodel.ExtensionViewModel


@Composable
fun ExtensionListScreen(
    navController: NavController,
    extensionViewModel: ExtensionViewModel = hiltViewModel<ExtensionViewModel>()
) {

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    extensionViewModel.getExtensions()
    val state = extensionViewModel.state

/*

    TopAppBar(
        backgroundColor = backgroundColor,
        title = { Text(
            text = "Title",
            style = MaterialTheme.typography.h6,
           // color = contentColor
        )},
        navigationIcon = { IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
              //  tint = contentColor
            )
        }},
        actions = {

                IconButton(onClick = {}) {
                    Icon(
                       painter = painterResource(id = R.drawable.sort),
                        contentDescription = "Share",
                        tint = Color.White
                    )
                }

        }
    )

 */



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

            items(state.extensionList.size) { i ->
                val extension = state.extensionList[i]
                ExtensionListItem(
                    extension = extension,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                if(i < state.extensionList.size) {
                    Divider(modifier = Modifier.padding(
                        horizontal = 16.dp
                    ))
                }
            }
        }
    }

}