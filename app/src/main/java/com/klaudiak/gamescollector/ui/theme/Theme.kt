package com.klaudiak.gamescollector.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Sand60,
    secondary = Sand100,
    error = Red80,
    onPrimary = NavyBlue80,
    onSecondary = NavyBlue80

)

private val LightColorPalette = lightColors(
    primary = Sand60,
    secondary = Sand100,
    error = Red80,
    onPrimary = NavyBlue80,
    onSecondary = NavyBlue100,
    onSurface = Sand60,
    background = Beige40
)
@Composable
fun GamesCollectorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}