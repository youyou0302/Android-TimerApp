package kr.co.presentation.main.timer.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.co.presentation.theme.TimerAppTheme

@Composable
fun TimerComponent(
    timerText: String,
    startState: Boolean,
    onStartToggleClick: () -> Unit,
    onClearClick: () -> Unit
) {
    val extraSize by animateDpAsState(
        if (startState) 200.dp else 160.dp, label = ""
    )
    val extraBackgroundColor by animateColorAsState(
        if (startState) Color.Red else Color.White, label = ""
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .size(extraSize)
            .border(1.dp, Color.Green)
            .padding(10.dp)
            .background(extraBackgroundColor)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = timerText,
            fontSize = 60.sp
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            StartToggleButtonComponent(
                startState = startState,
                onStartToggleClick = onStartToggleClick
            )
            Spacer(modifier = Modifier.padding(10.dp))
            ClearButtonComponent(
                startState = startState,
                onClearClick = onClearClick
            )
        }
    }
}

@Preview
@Composable
fun TimerComponentPreview() {
    TimerAppTheme {
        Surface {
            TimerComponent(
                timerText = "00:00.00",
                startState = false,
                onStartToggleClick = {},
                onClearClick = {}
            )
        }
    }
}