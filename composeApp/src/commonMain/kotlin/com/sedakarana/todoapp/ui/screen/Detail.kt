package com.sedakarana.todoapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sedakarana.todoapp.data.entity.TodoData
import com.sedakarana.todoapp.ui.theme.getFontBold
import com.sedakarana.todoapp.ui.theme.getFontMedium
import com.sedakarana.todoapp.ui.theme.getFontRegular
import com.sedakarana.todoapp.ui.viewmodel.DetailViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import todoapp.composeapp.generated.resources.Res
import todoapp.composeapp.generated.resources.back

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    detailViewModel: DetailViewModel = koinViewModel<DetailViewModel>(),
    todoData: TodoData
) {

    val title = remember { mutableStateOf("") }

    LaunchedEffect(key1 = true) {
        title.value = todoData.title
    }
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                "Güncelleme Sayfası",
                fontFamily = getFontBold(),
                fontSize = 18.sp
            )
        }, navigationIcon = {
            IconButton(
                onClick = { navController.popBackStack() },
                content = {
                    Icon(
                        painter = painterResource(Res.drawable.back),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                })

        })
    }) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Lütfen Güncellemek İstediğiniz Görevi Düzenleyiniz",
                fontFamily = getFontMedium(),
                fontSize = 16.sp,
                modifier = Modifier.padding(10.dp),
                textAlign = TextAlign.Center
            )
            TextField(value = title.value, onValueChange = { title.value = it }, label = {
                Text(
                    "Görev Adı",
                    fontSize = 16.sp,
                    fontFamily = getFontRegular()
                )
            })

            Button(onClick = { detailViewModel.update(todoData.id, title.value, todoData.isCompleted) }) {
                Text(
                    "GÜNCELLE",
                    fontSize = 16.sp,
                    fontFamily = getFontBold()
                )
            }
        }
    }

}