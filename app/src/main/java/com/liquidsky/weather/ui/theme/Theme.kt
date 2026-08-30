package com.liquidsky.weather.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF4A90D9),
    onPrimary = Color.White,
    background = Color(0xFF0F0C29),
    onBackground = Color.White,
    surface = Color(0x33FFFFFF),
    onSurface = Color.White
)

@Composable
fun WeatherTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
