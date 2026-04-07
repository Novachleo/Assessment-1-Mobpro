package com.muhammadfarellyridwan00044.assessment1mobprotodolist.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.muhammadfarellyridwan00044.assessment1mobprotodolist.R
import com.muhammadfarellyridwan00044.assessment1mobprotodolist.data.Task
import com.muhammadfarellyridwan00044.assessment1mobprotodolist.ui.theme.Assessment1MobproToDoListTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    newTask: Task? = null
) {
    var expanded by remember { mutableStateOf(false) }
    val taskList = TaskRepository.taskList
    LaunchedEffect(newTask) {
        newTask?.let {
            taskList.add(it)
        }
    }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name)) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    actions = {
                        IconButton(onClick = { expanded = true }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Menu"
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("About") },
                                onClick = {
                                    expanded = false
                                    navController.navigate("about")
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Clear All") },
                                onClick = {
                                    expanded = false
                                    taskList.clear()
                                }
                            )
                        }
                    }
                )
                Divider(
                    color = MaterialTheme.colorScheme.outline,
                    thickness = 1.dp
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("add")
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { padding ->

        if (taskList.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Empty"
                )
                Text(stringResource(R.string.empty_task))
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(taskList) { task ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable {
                                val index = taskList.indexOf(task)
                                if (index != -1) {
                                    taskList[index] = task.copy(isDone = !task.isDone)
                                }
                            },
                        colors = CardDefaults.cardColors(
                            containerColor =
                                if (task.isDone)
                                    MaterialTheme.colorScheme.surfaceVariant
                                else
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = task.isDone,
                                onCheckedChange = { checked ->
                                    val index = taskList.indexOf(task)
                                    if (index != -1) {
                                        taskList[index] = task.copy(isDone = checked)
                                    }
                                }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    task.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    color =
                                        if (task.isDone)
                                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                                        else
                                            MaterialTheme.colorScheme.onSurface,
                                    textDecoration =
                                        if (task.isDone)
                                            TextDecoration.LineThrough
                                        else null
                                )
                                Text(
                                    task.note,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text("Priority: ${task.priority}")
                            }
                        }
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun HomeScreenPreview() {
    Assessment1MobproToDoListTheme {
        HomeScreen(navController = rememberNavController(),)
    }
}