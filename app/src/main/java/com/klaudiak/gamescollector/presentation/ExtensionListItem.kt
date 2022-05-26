package com.klaudiak.gamescollector.presentation

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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.klaudiak.gamescollector.domain.Extension


@Composable
fun ExtensionListItem(
    extension: Extension,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = extension.id,
            modifier = Modifier.padding(end = 10.dp))
        Spacer(modifier = Modifier.width(24.dp))
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(extension.image)
                .crossfade(true)
                .build(),
            contentDescription = "extension_image",
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
            extension.name?.let { Text(text = it, style = MaterialTheme.typography.h5) }
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Released: ${extension.released}", style = MaterialTheme.typography.body1)
                Spacer(modifier = Modifier.width(8.dp))
            }
        }


    }
}