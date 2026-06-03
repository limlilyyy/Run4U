package com.example.run4u.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.run4u.ui.components.FoodCategoryCard
import com.example.run4u.ui.components.PieChart
import com.example.run4u.viewmodel.CaloriesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodListScreen(
    onAddFoodClick: () -> Unit,
    onClearClick: () -> Unit,
    onBackClick: () -> Unit, // Back navigation handler
    viewModel: CaloriesViewModel
) {
    val foodList by viewModel.foodList.collectAsState(initial = emptyList())
    val categoryGroups = foodList.groupBy { it.category }
    val totalCalories by viewModel.totalCalories.collectAsState(initial = 0)

    val categoryColors = mapOf(
        "Sugars" to androidx.compose.ui.graphics.Color(0xFFE57373),
        "Proteins" to androidx.compose.ui.graphics.Color(0xFF64B5F6),
        "Carbohydrates" to androidx.compose.ui.graphics.Color(0xFF81C784),
        "Fats" to androidx.compose.ui.graphics.Color(0xFFFFD54F),
        "Fruits" to androidx.compose.ui.graphics.Color(0xFFFF8A65),
        "Vegetables" to androidx.compose.ui.graphics.Color(0xFFBA68C8),
        "Dairy" to androidx.compose.ui.graphics.Color(0xFFA1887F),
        "Grains" to androidx.compose.ui.graphics.Color(0xFF4DB6AC),
        "Beverages" to androidx.compose.ui.graphics.Color(0xFF7986CB)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calories Calculator") },
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (foodList.isEmpty()) {
                Text("No food entries for today")
            } else {
                PieChart(
                    foodList = foodList,
                    onClearClick = onClearClick,
                    categoryColors = categoryColors,
                    modifier = Modifier.height(300.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("Total Calories: $totalCalories")

                Spacer(modifier = Modifier.height(16.dp))
                categoryGroups.forEach { (category, foods) ->
                    FoodCategoryCard(
                        category = category,
                        foods = foods,
                        color = categoryColors[category] ?: androidx.compose.ui.graphics.Color.Gray,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onClearClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Clear All Entries")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onAddFoodClick) {
                Text("Add Food")
            }
        }
    }
}
