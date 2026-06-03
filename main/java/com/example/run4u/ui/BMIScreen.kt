package com.example.run4u.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.run4u.viewmodel.BMIViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMIScreen(
    navController: NavController,
    viewModel: BMIViewModel,
    onBackClick: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") } // Height in cm
    var bmiResult by remember { mutableStateOf("") }
    var categoryResult by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("BMI Calculator") },
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
            // Input field for user name
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            // Input field for weight
            OutlinedTextField(
                value = weight,
                onValueChange = { weight = it },
                label = { Text("Weight (kg)") },
                modifier = Modifier.fillMaxWidth()
            )

            // Input field for height
            OutlinedTextField(
                value = height,
                onValueChange = { height = it },
                label = { Text("Height (cm)") }, // Updated to take height in cm
                modifier = Modifier.fillMaxWidth()
            )

            // Calculate BMI button
            Button(
                onClick = {
                    if (name.isNotEmpty() && weight.isNotEmpty() && height.isNotEmpty()) {
                        val heightInMeters = height.toFloat() / 100 // Convert cm to meters
                        val bmi = weight.toFloat() / (heightInMeters * heightInMeters)
                        bmiResult = "BMI: %.2f".format(bmi)
                        categoryResult = when {
                            bmi < 18.5 -> "Category: Underweight"
                            bmi in 18.5..24.9 -> "Category: Normal"
                            bmi in 25.0..29.9 -> "Category: Overweight"
                            else -> "Category: Obese"
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calculate BMI")
            }

            // Display BMI result
            Text(text = bmiResult)
            Text(text = categoryResult)

            // Save BMI button
            Button(
                onClick = {
                    if (name.isNotEmpty() && bmiResult.isNotEmpty() && categoryResult.isNotEmpty()) {
                        coroutineScope.launch {
                            viewModel.insert(
                                com.example.run4u.data.BMIRecordEntity(
                                    name = name,
                                    date = "Today",
                                    bmi = bmiResult.split(":")[1].trim(),
                                    category = categoryResult.split(":")[1].trim()
                                )
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save BMI")
            }

            // Navigate to BMI history screen
            Button(
                onClick = { navController.navigate("bmi_history") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("View BMI History")
            }
        }
    }
}
