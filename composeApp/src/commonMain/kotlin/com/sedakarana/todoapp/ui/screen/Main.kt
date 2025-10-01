package com.sedakarana.todoapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sedakarana.todoapp.data.entity.TodoData
import com.sedakarana.todoapp.ui.theme.colorBlack
import com.sedakarana.todoapp.ui.theme.colorGreen
import com.sedakarana.todoapp.ui.theme.colorOrange
import com.sedakarana.todoapp.ui.theme.colorRed
import com.sedakarana.todoapp.ui.theme.colorWhite
import com.sedakarana.todoapp.ui.theme.getFontBold
import com.sedakarana.todoapp.ui.theme.getFontRegular
import com.sedakarana.todoapp.ui.viewmodel.AddViewModel
import com.sedakarana.todoapp.ui.viewmodel.MainViewModel
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import todoapp.composeapp.generated.resources.Res
import todoapp.composeapp.generated.resources.check
import todoapp.composeapp.generated.resources.close
import todoapp.composeapp.generated.resources.delete
import todoapp.composeapp.generated.resources.plus
import todoapp.composeapp.generated.resources.search
import todoapp.composeapp.generated.resources.uncheck

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel { MainViewModel() }
) {
    val todoList = viewModel.todoList.collectAsState()
    var search = remember { mutableStateOf("") }
    var searchStatus = remember { mutableStateOf(false) }
    LaunchedEffect(key1 = true) {
        viewModel.loadAll()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (searchStatus.value) {
                        TextField(value = search.value, onValueChange = {
                            search.value = it
                            viewModel.search(search.value)
                        }, label = {
                            Text(
                                text = "Arama Yapınız",
                                fontSize = 18.sp,
                                fontFamily = getFontBold()
                            )
                        })
                    } else {
                        Text(
                            text = "Todo App Ana Sayfa",
                            fontSize = 18.sp,
                            fontFamily = getFontBold()
                        )
                    }

                },
                actions = {
                    IconButton(
                        onClick = {
                            if (searchStatus.value) {
                                searchStatus.value = false
                                search.value = ""
                                viewModel.loadAll()

                            } else {
                                searchStatus.value = true
                            }
                        },
                        content = {
                            Icon(
                                painter = if (searchStatus.value) painterResource(Res.drawable.close) else painterResource(
                                    Res.drawable.search
                                ),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        })

                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("add")
                },
                content = {
                    Icon(
                        painter = painterResource(Res.drawable.plus),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                })
        }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            LazyColumn() {
                items(todoList.value.size) { index ->
                    val item = todoList.value[index]
                    Card(
                        modifier = Modifier.fillMaxWidth()
                            .height(100.dp)
                            .padding(10.dp)
                            .clickable {
                                val sendItem = Json.encodeToString(item)
                                navController.navigate("detail/$sendItem")
                            }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize()
                                .background(colorWhite)
                        ) {
                            Box(
                                Modifier.fillMaxHeight()
                                    .width(50.dp)
                                    .clickable {
                                        viewModel.updateStatus(item.id)
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = if (item.isCompleted) painterResource(Res.drawable.check) else painterResource(
                                        Res.drawable.uncheck
                                    ),
                                    tint = if (item.isCompleted) colorGreen else colorOrange,
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                        .align(Alignment.Center)
                                )

                            }
                            Box(
                                Modifier.fillMaxHeight()
                                    .weight(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = item.title,
                                    fontSize = 16.sp,
                                    fontFamily = getFontRegular(),
                                    color = colorBlack
                                )
                            }
                            Box(
                                Modifier.fillMaxHeight()
                                    .width(50.dp)
                                    .clickable {
                                        viewModel.deleteTask(item.id)
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(Res.drawable.delete),
                                    tint = colorRed,
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                        .align(Alignment.Center)
                                )

                            }

                        }
                    }
                }
            }
        }


    }
}

