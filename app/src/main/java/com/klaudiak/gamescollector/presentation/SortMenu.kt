package com.klaudiak.gamescollector.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.klaudiak.gamescollector.R

@Composable
fun SortMenu(ifGames:Boolean, bodyContent: MutableState<SortOpt>) {
    val expanded = remember { mutableStateOf(false) } // 1

    Box(
        Modifier
            .wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = {
            expanded.value = true // 2
            bodyContent.value =  SortOpt.UNSORTED
        }) {
            Icon(
                painter = painterResource(id = R.drawable.sort),
                contentDescription = "Options",
                tint = MaterialTheme.colors.onSecondary
            )
        }
    }

    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false },
    ) {
        DropdownMenuItem(onClick = {
            expanded.value = false // 3
            bodyContent.value = SortOpt.RELEASE_YEAR // 4
        }) {
            Text("Sort by release year",  color = Color.Black)
        }

        Divider()

        DropdownMenuItem(onClick = {
            expanded.value = false
            bodyContent.value = SortOpt.TITLE
        }) {
            Text("Sort by title", color = Color.Black)
        }

        if(ifGames){
            Divider()

            DropdownMenuItem(onClick = {
                expanded.value = false
                bodyContent.value = SortOpt.RATING
            }) {
                Text("Sort by rating",  color = Color.Black)
            }
        }



    }
}

enum class SortOpt{
    UNSORTED, RELEASE_YEAR, TITLE, RATING
}