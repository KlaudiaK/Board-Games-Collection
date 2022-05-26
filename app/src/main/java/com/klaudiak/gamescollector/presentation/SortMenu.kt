package com.klaudiak.gamescollector.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
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
fun SortMenu(bodyContent: MutableState<SortOpt>) {
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
                tint = Color.White
            )
        }
    }

    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false },
    ) {
        DropdownMenuItem(onClick = {
            expanded.value = false // 3
            bodyContent.value = SortOpt.ID // 4
        }) {
            Text("Sort by id")
        }

        Divider()

        DropdownMenuItem(onClick = {
            expanded.value = false
            bodyContent.value = SortOpt.TITLE
        }) {
            Text("Sort by title")
        }

        Divider()

        DropdownMenuItem(onClick = {
            expanded.value = false
            bodyContent.value = SortOpt.RATING
        }) {
            Text("Sort by rating")
        }


    }
}

enum class SortOpt{
    UNSORTED, ID, TITLE, RATING
}