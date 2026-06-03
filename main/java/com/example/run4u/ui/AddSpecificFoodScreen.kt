package com.example.run4u.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.run4u.viewmodel.CaloriesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSpecificFoodScreen(
    category: String,
    onBackClick: () -> Unit,
    viewModel: CaloriesViewModel
) {
    val foodName = remember { mutableStateOf("") }
    val calories = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Food to $category") },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = foodName.value,
                onValueChange = { foodName.value = it },
                label = { Text("Food Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = calories.value,
                onValueChange = { calories.value = it },
                label = { Text("Calories") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (foodName.value.isNotEmpty() && calories.value.isNotEmpty()) {
                        viewModel.addFood(
                            name = foodName.value,
                            category = category,
                            calories = calories.value.toInt()
                        )
                        onBackClick()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Food")
            }
        }
    }
}
