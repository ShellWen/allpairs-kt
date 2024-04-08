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

package com.shellwen.allpairs

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shellwen.allpairs.component.AppContent
import com.shellwen.allpairs.component.AppScaffold
import com.shellwen.allpairs.entity.Param
import com.shellwen.allpairs.theme.AppTheme

@Composable
fun App() {
    val isSystemDark = isSystemInDarkTheme()
    var isDark by remember { mutableStateOf(isSystemDark) }

    val toggleDark = {
        isDark = !isDark
    }

    val paramList: MutableList<Param> = remember {
        mutableStateListOf<Param>().apply {
            add(Param("a", sortedSetOf("1", "2", "3")))
            add(Param("b", sortedSetOf("4", "5", "6")))
        }
    }

    val onFabClick = {
        paramList.add(Param())
        Unit
    }

    AppTheme(isDark) {
        AppScaffold(isDark, toggleDark, onFabClick) { padding ->
            AppContent(
                modifier = Modifier.padding(16.dp).padding(padding),
                paramList = paramList,
                onUpdateParam = { index, it ->
                    paramList[index] = it
                },
                onParamRemove = {
                    paramList.remove(it)
                }
            )
        }
    }
}
