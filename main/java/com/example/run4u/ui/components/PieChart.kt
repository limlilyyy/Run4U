package com.example.run4u.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.run4u.data.Food
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun PieChart(
    foodList: List<Food>,
    onClearClick: () -> Unit,
    categoryColors: Map<String, Color>, // Accept categoryColors as a parameter
    modifier: Modifier = Modifier
) {
    if (foodList.isEmpty()) return

    val categoryGroups = foodList.groupBy { it.category }
    val totalCalories = foodList.sumOf { it.calories }
    val caloriePercentages = categoryGroups.mapValues { entry ->
        entry.value.sumOf { it.calories }.toFloat() / totalCalories
    }

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Canvas(modifier = Modifier.size(300.dp).align(Alignment.CenterHorizontally)) {
            val diameter = size.minDimension
            val radius = diameter / 2
            var startAngle = -90f

            caloriePercentages.forEach { (category, percentage) ->
                val sweepAngle = percentage * 360f
                var color = categoryColors[category] ?: Color.Gray

                // Draw pie segment
                drawArc(
                    color = color,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = true,
                    size = Size(diameter, diameter)
                )

                // Draw category labels
                val angle = Math.toRadians((startAngle + sweepAngle / 2).toDouble())
                val labelRadius = radius * 0.7f
                val labelX = (center.x + labelRadius * cos(angle)).toFloat()
                val labelY = (center.y + labelRadius * sin(angle)).toFloat()

                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        category,
                        labelX,
                        labelY,
                        android.graphics.Paint().apply {
                            color = categoryColors[category] ?: Color.Black
                            textSize = 14.sp.toPx()
                            textAlign = android.graphics.Paint.Align.CENTER
                        }
                    )
                }
                startAngle += sweepAngle
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Clear All Entries Button
        androidx.compose.material3.Button(
            onClick = onClearClick,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        ) {
            androidx.compose.material3.Text("Clear All Entries")
        }
    }
}
