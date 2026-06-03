package com.example.run4u.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home", style = MaterialTheme.typography.titleLarge) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp), // Spaced evenly with gaps between items
            horizontalAlignment = Alignment.Start // Align buttons to the start
        ) {
            CustomButton(
                text = "BMI Calculator",
                gradientColors = listOf(Color(0xFF4CAF50), Color(0xFF2E7D32)), // Green gradient
                onClick = { navController.navigate("bmi_calculator") }
            )

            CustomButton(
                text = "Calories Calculator",
                gradientColors = listOf(Color(0xFF2196F3), Color(0xFF1976D2)), // Blue gradient
                onClick = { navController.navigate("calories_home") }
            )

            CustomButton(
                text = "Workout Videos",
                gradientColors = listOf(Color(0xFFFF9800), Color(0xFFF57C00)),
                onClick = { navController.navigate("workout_home") }
            )

            CustomButton(
                text = "Music Playlist",
                gradientColors = listOf(Color(0xFF009688), Color(0xFF4CAF50)),
                onClick = { navController.navigate("music_playlist") }
            )

            CustomButton(
                text = "Diet Planner",
                gradientColors = listOf(Color(0xFFFF5722), Color(0xFFF44336)),
                onClick = { navController.navigate("diet_planner") }
            )

            CustomButton(
                text = "Step Tracker",
                gradientColors = listOf(Color(0xFF9C27B0), Color(0xFF673AB7)),
                onClick = { navController.navigate("step_tracker") }
            )

        }
    }
}

@Composable
fun CustomButton(
    text: String,
    gradientColors: List<Color>,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp), // Slightly taller buttons for better visibility
        shape = RoundedCornerShape(12.dp), // Rounded corners for modern look
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(0.dp) // Remove default padding to allow gradient fill
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(gradientColors),
                    shape = RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = Color.White, // High contrast text color
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
