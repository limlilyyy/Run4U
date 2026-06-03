package com.example.run4u.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodCategoryScreen(
    onCategoryClick: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val categories = listOf(
        "Sugars",
        "Proteins",
        "Carbohydrates",
        "Fats",
        "Fruits",
        "Vegetables",
        "Dairy",
        "Grains",
        "Beverages"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select Food Category") },
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            categories.forEach { category ->
                Button(
                    onClick = { onCategoryClick(category) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Text(
                        text = category,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

