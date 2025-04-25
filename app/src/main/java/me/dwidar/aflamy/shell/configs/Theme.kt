package me.dwidar.aflamy.shell.configs

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = primaryPurple,
    secondary = secondaryPurple,
    tertiary = accentColor,
    background = backgroundColor,
    surface = backgroundColor,
    onSurface = white
)

@Composable
fun AflamyTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}