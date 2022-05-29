package com.klaudiak.gamescollector.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.klaudiak.gamescollector.R


@Composable
fun SyncDialog(
    lastSyncDate: String,
    show: Boolean,
    showProgress: Progress,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if(show) {
        Dialog(onDismissRequest = onDismiss) {
            SyncDialogUI(lastSyncDate = lastSyncDate,showProgress = showProgress,onDismiss = onDismiss, onConfirm = onConfirm)
        }
    }
}


@Composable
fun SyncDialogUI(
    modifier: Modifier = Modifier,
    lastSyncDate: String,
    showProgress: Progress,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
){

    Card(
        //shape = MaterialTheme.shapes.medium,
        shape = RoundedCornerShape(10.dp),
        // modifier = modifier.size(280.dp, 240.dp)
        modifier = Modifier.padding(10.dp,5.dp,10.dp,10.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier
                .background(Color.White)) {


            ProgressIndicator(state = showProgress)
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Last update was $lastSyncDate",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                   // style = MaterialTheme.typography.labelLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black
                )
                Text(
                    text = "Are you sure you want to synchronize data?",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                        .fillMaxWidth(),
                    color = Color.Black
                )
            }
            //.......................................................................
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(MaterialTheme.colors.onPrimary),
                horizontalArrangement = Arrangement.SpaceAround) {

                TextButton(onClick =  onDismiss) {

                    Text(
                        "Later",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onBackground,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }
                TextButton(onClick = onConfirm) {
                    Text(
                        "Yes",
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }
            }
        }
    }
}
@Composable
fun ProgressIndicator(
    state: Progress,

) {
    when(state) {
        Progress.BEFORE -> Image(
            painter = painterResource(id = R.drawable.synchronize),
            contentDescription = null, // decorative
            contentScale = ContentScale.Fit,
            colorFilter  = ColorFilter.tint(
                color = MaterialTheme.colors.onPrimary
            ),
            modifier = Modifier
                .padding(top = 35.dp)
                .height(70.dp)
                .fillMaxWidth(),

            )

        Progress.DURING ->
            CircularProgressIndicator(
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .padding(top = 45.dp, bottom = 20.dp, start = 130.dp, end = 50.dp)

                    //.fillMaxWidth()
            )


        Progress.AFTER -> Image(
            painter = painterResource(id = R.drawable.synchronize),
            contentDescription = null, // decorative
            contentScale = ContentScale.Fit,
            colorFilter  = ColorFilter.tint(
                color = MaterialTheme.colors.onPrimary
            ),
            modifier = Modifier
                .padding(top = 35.dp)
                .height(70.dp)
                .fillMaxWidth(),

            )
    }
}

enum class Progress{
    BEFORE, DURING, AFTER
}

