package kr.co.presentation.main.timer.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StartToggleButtonComponent(
    startState: Boolean,
    onStartToggleClick: () -> Unit,
) {
    TextButton(
        modifier = Modifier
            .background(Color.Black)
            .size(width = 150.dp, height = 70.dp),
        onClick = onStartToggleClick
    ) {
        if (startState) {
            Text(
                color = Color.White,
                text = "STOP",
                fontSize = 30.sp
            )
        } else {
            Text(
                color = Color.White,
                text = "START",
                fontSize = 30.sp
            )
        }
    }
}