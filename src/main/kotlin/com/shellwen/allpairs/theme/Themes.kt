/*
 * Copyright (c) 2024 ShellWen Chen.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shellwen.allpairs.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AppTheme(
    isDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDark) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}

private val LightColorPalette =
    lightColorScheme(
//        background = Color(0xFFF2F3F5),
//        onBackground = Color(0xFFABB1B8),
//        secondary = Color(0xFF5765F2),
//        primary = Color(0xFF5765F2),
//        surface = Color(0xFFFFFFFF),
//        onSurface = Color(0xFF4F5660),
//        onPrimary = Color(0xFFFFFFFF),
//        onSecondary = Color(0xFFE3E5E8),
    )

private val DarkColorPalette =
    darkColorScheme(
//        background = Color(0xFF1F2225),
//        onBackground = Color(0xFFABB1B8),
//        secondary = Color(0xFF5765F2),
//        primary = Color(0xFF5765F2),
//        surface = Color(0xFF1F2225),
//        onSurface = Color(0xFFABB1B8),
//        onPrimary = Color(0xFFFFFFFF),
//        onSecondary = Color(0xFF40444B),
    )