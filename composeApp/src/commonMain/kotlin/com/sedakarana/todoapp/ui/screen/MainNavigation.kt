package com.sedakarana.todoapp.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sedakarana.todoapp.data.entity.TodoData
import kotlinx.serialization.json.Json

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController = navController)
        }

        composable("add") {
            AddScreen(navController = navController)
        }

        composable(
            "detail/{todo}", arguments = listOf(
                navArgument("todo") {
                    type = NavType.StringType
                }
            )) { it ->
            val json = it.savedStateHandle.get<String>("todo")
            if (json != null) {
                val sendObject = Json.decodeFromString<TodoData>(json)
                DetailScreen(navController = navController, todoData = sendObject)
            }
        }

    }
}