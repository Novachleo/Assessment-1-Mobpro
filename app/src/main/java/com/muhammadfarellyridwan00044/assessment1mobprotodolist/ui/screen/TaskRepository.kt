package com.muhammadfarellyridwan00044.assessment1mobprotodolist.ui.screen

import androidx.compose.runtime.mutableStateListOf
import com.muhammadfarellyridwan00044.assessment1mobprotodolist.data.Task

object TaskRepository {
    val taskList = mutableStateListOf<Task>()
}