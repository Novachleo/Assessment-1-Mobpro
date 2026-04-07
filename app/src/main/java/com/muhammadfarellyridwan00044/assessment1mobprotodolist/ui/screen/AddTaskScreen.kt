package com.muhammadfarellyridwan00044.assessment1mobprotodolist.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.muhammadfarellyridwan00044.assessment1mobprotodolist.R
import com.muhammadfarellyridwan00044.assessment1mobprotodolist.data.Task
import com.muhammadfarellyridwan00044.assessment1mobprotodolist.ui.theme.Assessment1MobproToDoListTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(navController: NavController) {
    var title by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf("Low") }
    var titleError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text(stringResource(R.string.add_task)) }
                )
                Divider(
                    color = MaterialTheme.colorScheme.outline,
                    thickness = 1.dp
                )
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                    titleError = false
                },
                label = { Text(stringResource(R.string.title)) },
                isError = titleError,
                modifier = Modifier.fillMaxWidth()
            )

            if (titleError) {
                Text(
                    text = "Title tidak boleh kosong",
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 🔹 NOTE INPUT
            OutlinedTextField(
                value = note,
                onValueChange = { note = it },
                label = { Text(stringResource(R.string.note)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Priority")

            Row {
                listOf("Low", "Medium", "High").forEach { item ->
                    Row(
                        modifier = Modifier.padding(end = 8.dp),
                        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = priority == item,
                            onClick = { priority = item }
                        )
                        Text(item)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (title.isEmpty()) {
                        titleError = true
                    } else {
                        TaskRepository.taskList.add(
                            Task(0, title, note, priority)
                        )
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.save))
            }
        }
    }
}
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun AddTaskScreenPreview() {
    Assessment1MobproToDoListTheme {
        AddTaskScreen(navController = rememberNavController())
    }
}