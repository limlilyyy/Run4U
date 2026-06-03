package com.example.run4u.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.run4u.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutHomeScreen(onCategoryClick: (String) -> Unit, onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Workout Categories") },
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
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val categories = listOf(
                "Legs" to R.drawable.legs,
                "Abs" to R.drawable.abs,
                "Chest" to R.drawable.chest,
                "Back" to R.drawable.back,
                "Arms" to R.drawable.arms,
                "Full Body Workout" to R.drawable.fullbody,
                "Cardio" to R.drawable.cardio
            )

            items(categories.size) { index ->
                val category = categories[index]
                CategoryCard(
                    categoryName = category.first,
                    categoryIcon = category.second,
                    onClick = { onCategoryClick(category.first) }
                )
            }
        }
    }
}

@Composable
fun CategoryCard(categoryName: String, categoryIcon: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = categoryIcon),
                contentDescription = categoryName,
                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .background(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(8.dp)
            ) {
                Text(
                    text = categoryName,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
