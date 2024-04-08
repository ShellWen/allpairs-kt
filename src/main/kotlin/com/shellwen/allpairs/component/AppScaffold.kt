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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shellwen.allpairs.theme.AppTheme

@Composable
fun AppScaffold(
    isDark: Boolean,
    toggleDark: () -> Unit,
    onFabClick: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar(title = {
                Text("Allpairs")
            }, isDark = isDark, toggleDark = toggleDark)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onFabClick, modifier = Modifier.padding(bottom = 64.dp)) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },
        content = content
    )
}

@Preview
@Composable
fun AppScaffoldLightPreview() {
    AppTheme(isDark = false) {
        AppScaffold(isDark = false, toggleDark = {}, onFabClick = {}) { padding ->
            Text("Hello, Allpairs!", modifier = Modifier.padding(padding))
        }
    }
}

@Preview
@Composable
fun AppScaffoldDarkPreview() {
    AppTheme(isDark = true) {
        AppScaffold(isDark = true, toggleDark = {}, onFabClick = {}) { padding ->
            Text("Hello, Allpairs!", modifier = Modifier.padding(padding))
        }
    }
}