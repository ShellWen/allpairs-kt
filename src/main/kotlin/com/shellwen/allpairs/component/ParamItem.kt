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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shellwen.allpairs.entity.Param

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ParamItem(
    param: Param,
    onUpdate: (Param) -> Unit,
    onClickRemove: () -> Unit,
    paramNumber: Int,
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf(param.name) }
    var values by remember { mutableStateOf(param.values.joinToString(" ")) }

    val isNameValid = name.isNotBlank()
    val splitValues = remember(values) { values.split(" ").filterNot { it.isBlank() }.toSortedSet() }
    val isValuesValid = splitValues.isNotEmpty()

    DisposableEffect(param, name, values) {
        onUpdate(param.copy(name = name, values = splitValues))

        onDispose { }
    }

    Card(modifier = modifier) {
        FlowRow(
            Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("因子-$paramNumber") },
                isError = !isNameValid,
                supportingText = {
                    if (!isNameValid) {
                        Text("因子名不能为空")
                    }
                },
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = values,
                onValueChange = { values = it },
                label = { Text("所有因子") },
                isError = !isValuesValid,
                supportingText = {
                    if (!isValuesValid) {
                        Text("因子值不能为空")
                    }
                },
                modifier = Modifier.weight(3f)
            )
        }
    }
}