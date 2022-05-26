package com.klaudiak.gamescollector.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.klaudiak.gamescollector.domain.Game
import com.klaudiak.gamescollector.ui.theme.GamesCollectorTheme


@Composable
fun GameListItem(
    game: Game,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
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


    }
}




