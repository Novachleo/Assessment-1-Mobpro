package com.muhammadfarellyridwan00044.assessment1mobprotodolist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.muhammadfarellyridwan00044.assessment1mobprotodolist.data.Task
import com.muhammadfarellyridwan00044.assessment1mobprotodolist.ui.screen.*
import com.muhammadfarellyridwan00044.assessment1mobprotodolist.ui.screen.AddTaskScreen
import com.muhammadfarellyridwan00044.assessment1mobprotodolist.ui.screen.DetailScreen
import com.muhammadfarellyridwan00044.assessment1mobprotodolist.ui.screen.HomeScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("add") { AddTaskScreen(navController) }
        composable("about") { AboutScreen(navController) }
        composable("detail/{title}/{note}/{priority}") { backStackEntry ->
            DetailScreen(navController, backStackEntry)
        }
    }
}