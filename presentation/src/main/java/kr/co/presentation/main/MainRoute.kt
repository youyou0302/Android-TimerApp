package kr.co.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

enum class MainRoute(
    val route: String,
    val contentDescription: String,
    val icon: ImageVector
) {
    TIMER(route = "TimerScreen", contentDescription = "타이머", icon = Icons.Filled.Edit),
    INFORMATION(route = "InformationScreen", contentDescription = "정보", icon = Icons.Filled.Info)
}