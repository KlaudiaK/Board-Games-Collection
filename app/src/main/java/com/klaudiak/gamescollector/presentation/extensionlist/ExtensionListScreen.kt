package com.klaudiak.gamescollector.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    val bodyContent = remember { mutableStateOf(SortOpt.UNSORTED) }
extensionViewModel.getExtensions(bodyContent)
    Scaffold(
        topBar =

        {
            TopAppBar(
                backgroundColor = SnackbarDefaults.backgroundColor,
                title = {
                    Text(
                        text = "Title",
                        style = MaterialTheme.typography.h6,
                        // color = contentColor
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
                    SortMenu(ifGames = false,bodyContent = bodyContent)

                }

            )
        },

        ) {


            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                TopAppBar {
                    Row(modifier = Modifier.padding(start = 20.dp).fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ){
                        Text(text = "Id", color = Color.Black)
                        Text(text = "Logo", color = Color.Black)
                        Text(text = "Title",  color = Color.Black)
                        Spacer(modifier = Modifier.padding(end = 30.dp))
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

}