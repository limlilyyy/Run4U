package com.example.run4u.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.run4u.data.WorkoutVideo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoListScreen(
    category: String,
    videos: List<WorkoutVideo>,
    onVideoClick: (String) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Videos for $category") },
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
            items(videos.size) { index ->
                val video = videos[index]
                VideoCard(video = video, onClick = { onVideoClick(video.videoId) })
            }
        }
    }
}

@Composable
fun VideoCard(video: WorkoutVideo, onClick: (String) -> Unit) {
    var isExpanded by remember { mutableStateOf(false) } // State to track expansion

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 8.dp)
            .clickable { isExpanded = !isExpanded }, // Toggle expansion on click
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column {
            // Thumbnail Image
            Image(
                painter = rememberAsyncImagePainter(video.thumbnailUrl),
                contentDescription = video.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp) // Fixed height for the thumbnail
            )

            // Animated expansion
            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Video Title
                    Text(
                        text = video.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Video Description
                    Text(
                        text = video.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Watch Video Button
                    Button(
                        onClick = { onClick(video.videoId) },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Watch Video")
                    }
                }
            }
        }
    }
}
