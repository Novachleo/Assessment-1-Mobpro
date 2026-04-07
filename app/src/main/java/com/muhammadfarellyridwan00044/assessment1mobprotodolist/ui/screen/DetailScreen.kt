package com.muhammadfarellyridwan00044.assessment1mobprotodolist.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavBackStackEntry
import android.content.Intent
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.navigation.testing.TestNavHostController
import com.muhammadfarellyridwan00044.assessment1mobprotodolist.R
import com.muhammadfarellyridwan00044.assessment1mobprotodolist.ui.theme.Assessment1MobproToDoListTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, backStackEntry: NavBackStackEntry) {
    val title = backStackEntry.arguments?.getString("title") ?: ""
    val note = backStackEntry.arguments?.getString("note") ?: ""
    val priority = backStackEntry.arguments?.getString("priority") ?: ""

    DetailContent(
        navController = navController,
        title = title,
        note = note,
        priority = priority
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContent(
    navController: NavController,
    title: String,
    note: String,
    priority: String
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(text = stringResource(R.string.detail_task))
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }

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
            Text("Title: $title", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Note: $note")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Priority: $priority")
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val taskToRemove = TaskRepository.taskList.find {
                        it.title == title &&
                                it.note == note &&
                                it.priority == priority
                    }

                    taskToRemove?.let {
                        TaskRepository.taskList.remove(it)
                    }

                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.delete))
            }

            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, "Task: $title\n$note\nPriority: $priority")
                    }
                    context.startActivity(Intent.createChooser(intent, "Share Task"))
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.share))
            }
        }
    }
}
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailPreview() {
    Assessment1MobproToDoListTheme {
        DetailContent(
            navController = rememberNavController(),
            title = "",
            note = "",
            priority = ""
        )
    }
}