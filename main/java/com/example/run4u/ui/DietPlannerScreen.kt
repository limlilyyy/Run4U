package com.example.run4u.ui

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.run4u.viewmodel.CaloriesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DietPlannerScreenLayout(
    navController: NavController,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CaloriesViewModel
) {
    val totalCalories by viewModel.totalCalories.collectAsState(initial = 0)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Diet Planner") },
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
            modifier = modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()), // Make the column scrollable
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Card(
                modifier = Modifier
                    .padding(vertical = 36.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        fontSize = 50.sp,
                        textAlign = TextAlign.Center,
                        text = "Calories Consumed",
                        style = MaterialTheme.typography.displayLarge
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        color = Color.Blue,
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center,
                        text = "$totalCalories kcal",
                        style = MaterialTheme.typography.displayLarge
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Start,
                fontSize = 30.sp,
                text = "Food Suggestion",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(15.dp))

            // Recipe Cards Section
            recipes.forEach { recipe ->
                RecipeCard(recipe = recipe)
            }
        }
    }
}


// Recipe Card
@Composable
fun RecipeCard(
    recipe: Recipe,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier.padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RecipeIcon(recipe.imageResourceId)
                RecipeInformation(recipe.name)
                Spacer(Modifier.weight(1f)) // Spacer between text and button
                RecipeItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded },
                )
            }
            if (expanded) {
                RecipeDetails(
                    recipe.details,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
private fun RecipeItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = if (expanded) "▲" else "▼", // Unicode symbols for arrows
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

// Recipe Icon
@Composable
fun RecipeIcon(
    @DrawableRes imageResourceId: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = imageResourceId),
        contentDescription = null,
        modifier = modifier
            .size(64.dp)
            .clip(MaterialTheme.shapes.medium)
    )
}

// Recipe Information
@Composable
fun RecipeInformation(
    recipeName: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            fontSize = 20.sp,
            text = recipeName,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(8.dp)
        )
    }
}

// Recipe Details
@Composable
fun RecipeDetails(
    details: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "About:",
            color = Color.Blue,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = details,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

