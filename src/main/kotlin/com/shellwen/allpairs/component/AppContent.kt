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
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shellwen.allpairs.entity.Param
import com.shellwen.allpairs.util.toParameters
import com.shellwen.allpairs.util.validate
import io.github.pavelicii.allpairs4j.AllPairs
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppContent(
    paramList: List<Param>,
    onUpdateParam: (Int, Param) -> Unit,
    onParamRemove: (Param) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState { 2 }
    val scope = rememberCoroutineScope()
    // Damn it!
    // https://github.com/JetBrains/compose-multiplatform/issues/3447
    HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
        when (page) {
            0 -> {
                EditorPage(
                    paramList = paramList,
                    onUpdateParam = onUpdateParam,
                    onParamRemove = onParamRemove,
                    onGenerateClick = { scope.launch { pagerState.animateScrollToPage(1) } },
                    modifier = Modifier.fillMaxSize().then(modifier)
                )
            }

            1 -> {
                ResultPage(
                    paramList = paramList,
                    onBackClick = { scope.launch { pagerState.animateScrollToPage(0) } },
                    modifier = Modifier.fillMaxSize().then(modifier)
                )
            }
        }
    }
}

@Composable
fun EditorPage(
    paramList: List<Param>,
    onUpdateParam: (Int, Param) -> Unit,
    onParamRemove: (Param) -> Unit,
    onGenerateClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = Modifier.fillMaxSize().then(modifier)) {
        Column(
            Modifier.fillMaxHeight().weight(1f).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            paramList.forEachIndexed { index, param ->
                ParamItem(
                    param = param,
                    onUpdate = { onUpdateParam(index, it) },
                    onClickRemove = { onParamRemove(param) },
                    paramNumber = index + 1,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Button(
            onClick = onGenerateClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("生成")
        }
    }
}

@Composable
fun ResultPage(
    paramList: List<Param>,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = Modifier.fillMaxSize().then(modifier)) {
        Column(
            Modifier.fillMaxHeight().weight(1f).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val isValid = paramList.validate()
            Text(
                "共 ${paramList.size} 个因子，全部合法：${
                    if (isValid) "是" else "否"
                }",
                modifier = Modifier.padding(top = 8.dp)
            )
            if (isValid) {
                val allPairs = remember(paramList) {
                    AllPairs.AllPairsBuilder().withParameters(paramList.toParameters()).build()
                }

                Text(
                    "$allPairs",
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("返回列表")
        }
    }
}

@Preview
@Composable
fun AppContentEmptyPreview() {
    AppContent(
        paramList = emptyList(),
        onUpdateParam = { _, _ -> },
        onParamRemove = {}
    )
}

@Preview
@Composable
fun AppContentPreview() {
    AppContent(
        paramList = listOf(
            Param("param1", sortedSetOf("1", "2", "3")),
            Param("param2", sortedSetOf("4", "5", "6")),
            Param("param3", sortedSetOf("7", "8", "9"))
        ),
        onUpdateParam = { _, _ -> },
        onParamRemove = {}
    )
}
