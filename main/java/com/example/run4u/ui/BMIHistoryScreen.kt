package com.example.run4u.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.run4u.viewmodel.BMIViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMIHistoryScreen(
    viewModel: BMIViewModel,
    onBackClick: () -> Unit
) {
    val records = viewModel.allRecords.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("BMI History") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(records.value) { record ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Name: ${record.name}", style = MaterialTheme.typography.bodyLarge)
                        Text("Date: ${record.date}", style = MaterialTheme.typography.bodyLarge)
                        Text("BMI: ${record.bmi}", style = MaterialTheme.typography.bodyLarge)
                        Text("Category: ${record.category}", style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = { viewModel.delete(record) }, // Ensure this method works
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }
}
