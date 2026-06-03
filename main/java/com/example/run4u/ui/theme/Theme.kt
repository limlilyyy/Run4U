package com.example.run4u.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun Run4UTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        androidx.compose.material3.darkColorScheme(
            primary = DarkPrimary,
            onPrimary = DarkOnPrimary,
            secondary = DarkSecondary,
            onSecondary = DarkOnSecondary,
            background = DarkBackground,
            onBackground = DarkOnBackground,
            surface = DarkSurface,
            onSurface = DarkOnSurface
        )
    } else {
        androidx.compose.material3.lightColorScheme(
            primary = LightPrimary,
            onPrimary = LightOnPrimary,
            secondary = LightSecondary,
            onSecondary = LightOnSecondary,
            background = LightBackground,
            onBackground = LightOnBackground,
            surface = LightSurface,
            onSurface = LightOnSurface
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}
