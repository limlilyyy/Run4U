package com.example.run4u.data

import android.content.Context
import com.example.run4u.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

data class WorkoutVideo(
    val videoId: String,
    val title: String,
    val category: String,
    val thumbnailUrl: String,
    val description: String
)

class VideoRepository(context: Context) {
    private val videos: List<WorkoutVideo> by lazy {
        val inputStream = context.resources.openRawResource(R.raw.workout_videos)
        val reader = InputStreamReader(inputStream)
        val type = object : TypeToken<List<WorkoutVideo>>() {}.type
        Gson().fromJson(reader, type)
    }

    fun getVideosByCategory(category: String): List<WorkoutVideo> =
        videos.filter { it.category == category }

    fun getVideoById(videoId: String): WorkoutVideo? =
        videos.find { it.videoId == videoId }
}
