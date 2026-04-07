package com.muhammadfarellyridwan00044.assessment1mobprotodolist.data

data class Task(
    val id: Int,
    val title: String,
    val note: String,
    val priority: String,
    var isDone: Boolean = false
)