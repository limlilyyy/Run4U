package com.example.run4u.ui.navigation

import SongAdapter
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.run4u.ui.*
import com.example.run4u.viewmodel.BMIViewModel
import com.example.run4u.viewmodel.CaloriesViewModel
import com.example.run4u.viewmodel.CaloriesViewModelFactory
import com.example.run4u.respository.CaloriesRepository
import com.example.run4u.data.VideoRepository

@Composable
fun AppNavGraph(
    navController: NavHostController,
    caloriesRepository: CaloriesRepository,
    videoRepository: VideoRepository
) {
    NavHost(navController = navController, startDestination = "login") {
        // Login and Signup
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignupScreen(navController) }
        composable("home") { HomeScreen(navController) }

        // BMI Calculator
        composable("bmi_calculator") {
            val bmiViewModel: BMIViewModel = viewModel()
            BMIScreen(
                navController = navController,
                viewModel = bmiViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("bmi_history") {
            val bmiViewModel: BMIViewModel = viewModel()
            BMIHistoryScreen(
                viewModel = bmiViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
        // Calories Calculator
        composable("calories_home") {
            val caloriesViewModel: CaloriesViewModel = viewModel(
                factory = CaloriesViewModelFactory(caloriesRepository)
            )
            FoodListScreen(
                viewModel = caloriesViewModel,
                onAddFoodClick = { navController.navigate("calories_add_category") },
                onClearClick = { caloriesViewModel.clearAllEntries() },
                onBackClick = { navController.navigateUp() }
            )
        }
        composable("calories_add_category") {
            FoodCategoryScreen(
                onCategoryClick = { category ->
                    navController.navigate("calories_add_food/$category")
                },
                onBackClick = { navController.navigateUp() }
            )
        }
        composable("calories_add_food/{category}") { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: ""
            val caloriesViewModel: CaloriesViewModel = viewModel(
                factory = CaloriesViewModelFactory(caloriesRepository)
            )
            AddSpecificFoodScreen(
                category = category,
                viewModel = caloriesViewModel,
                onBackClick = { navController.navigateUp() }
            )
        }

        // Workout Video Screens
        composable("workout_home") {
            WorkoutHomeScreen(
                onCategoryClick = { category ->
                    navController.navigate("video_list/$category")
                },
                onBackClick = {
                    navController.popBackStack() // Navigates back to the previous screen
                }
            )
        }

        composable("video_list/{category}") { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: ""
            val videos = videoRepository.getVideosByCategory(category)
            VideoListScreen(
                category = category,
                videos = videos,
                onVideoClick = { videoId ->
                    navController.navigate("video_player/$videoId")
                },
                onBackClick = { navController.navigateUp() }
            )
        }
        composable("video_player/{videoId}") { backStackEntry ->
            val videoId = backStackEntry.arguments?.getString("videoId") ?: ""
            val video = videoRepository.getVideoById(videoId)
            if (video != null) {
                VideoPlayerScreen(video = video) { navController.navigateUp() }
            }
        }

        composable("diet_planner") {
            val caloriesViewModel: CaloriesViewModel = viewModel(
                factory = CaloriesViewModelFactory(caloriesRepository)
            )
            DietPlannerScreenLayout(
                navController = navController,
                viewModel = caloriesViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("step_tracker") {
            val context = LocalContext.current
            LaunchedEffect(Unit) {
                val intent = Intent(context, fitcount::class.java)
                context.startActivity(intent)
            }
        }

        composable("music_playlist") {
            val context = LocalContext.current
            LaunchedEffect(Unit) {
                val intent = Intent(context, MusicPlaylist::class.java)
                context.startActivity(intent)
            }
        }
    }
}
