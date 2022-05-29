package com.klaudiak.gamescollector.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.klaudiak.gamescollector.domain.Game


@Composable
fun GameListItem(
    game: Game,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable (
                enabled = true,
                onClick = {
                     navController.navigate(route = Screen.RankingHistoryScreen.passId(game.id))
                }

            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = game.id,
        modifier = Modifier.padding(end = 10.dp))
        Spacer(modifier = Modifier.width(24.dp))
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(game.image)
                .crossfade(true)
                .build(),
            contentDescription = "game_image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .weight(0.66f)
        )
        Spacer(modifier = Modifier.width(24.dp))

        Column(
            modifier = Modifier
                .weight(1f)
        ){
            game.name?.let { Text(text = it, style = MaterialTheme.typography.h5) }
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Released: ${game.released}", style = MaterialTheme.typography.body1)
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        Row{
            Text(text = "${game.rating}", style = MaterialTheme.typography.body1)
        }
    }
}




