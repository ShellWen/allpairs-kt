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

package com.shellwen.allpairs.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.shellwen.allpairs.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    isDark: Boolean,
    toggleDark: () -> Unit
) {
    TopAppBar(title = title, modifier = modifier, actions = {
        IconButton(onClick = toggleDark) {
            val icon = if (isDark) Icons.Default.LightMode else Icons.Default.DarkMode

            Icon(icon, contentDescription = "Toggle Dark Mode")
        }
    })
}

@Preview
@Composable
fun AppTopBarLightPreview() {
    AppTheme(isDark = false) {
        AppTopBar(title = {
            Text("Allpairs Light")
        }, isDark = false, toggleDark = {})
    }
}

@Preview
@Composable
fun AppTopBarDarkPreview() {
    AppTheme(isDark = true) {
        AppTopBar(title = {
            Text("Allpairs Dark")
        }, isDark = true, toggleDark = {})
    }
}
