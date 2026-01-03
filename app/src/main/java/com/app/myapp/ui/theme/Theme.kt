package com.app.myapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF6366F1), // Indigo 500
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFC7D2FE), // Indigo 200
    onPrimaryContainer = Color(0xFF1E293B), // Slate 800
    secondary = Color(0xFF4338CA), // Indigo 700
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFA5B4FC), // Indigo 300
    onSecondaryContainer = Color(0xFF1E293B),
    tertiary = Color(0xFF3730A3), // Indigo 800
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFF818CF8), // Indigo 400
    onTertiaryContainer = Color(0xFF1E293B),
    background = Color(0xFF1E293B), // Slate 800
    onBackground = Color(0xFFF1F5F9), // Slate 100
    surface = Color(0xFF1E293B), // Slate 800
    onSurface = Color(0xFFF1F5F9), // Slate 100
    error = Color(0xFFEF4444), // Red 500
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFECACA), // Red 200
    onErrorContainer = Color(0xFF7F1D1D) // Red 900
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6366F1), // Indigo 500
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFC7D2FE), // Indigo 200
    onPrimaryContainer = Color(0xFF1E293B), // Slate 800
    secondary = Color(0xFF4338CA), // Indigo 700
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFA5B4FC), // Indigo 300
    onSecondaryContainer = Color(0xFF1E293B),
    tertiary = Color(0xFF3730A3), // Indigo 800
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFF818CF8), // Indigo 400
    onTertiaryContainer = Color(0xFF1E293B),
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF1E293B), // Slate 800
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1E293B), // Slate 800
    error = Color(0xFFEF4444), // Red 500
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFECACA), // Red 200
    onErrorContainer = Color(0xFF7F1D1D) // Red 900
)

@Composable
fun MyAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), dynamicColor: Boolean = true, content: @Composable () -> Unit) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}