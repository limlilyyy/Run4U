package com.example.run4u

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.run4u.data.Run4UDatabase
import com.example.run4u.data.VideoRepository
import com.example.run4u.respository.CaloriesRepository
//import com.example.run4u.ui.LoginPage
import com.example.run4u.ui.navigation.AppNavGraph
import com.example.run4u.ui.theme.Run4UTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the database and repositories
        val database = Run4UDatabase.getDatabase(this)
        val caloriesRepository = CaloriesRepository(database.foodDao())
        val videoRepository = VideoRepository(this)
        enableEdgeToEdge()
        setContent {
            Run4UTheme {
                val navController = rememberNavController()
                AppNavGraph(
                    navController = navController,
                    caloriesRepository = caloriesRepository,
                    videoRepository = videoRepository

                )

            }
        }
    }}

