package kr.co.presentation.main.timer.component

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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
    Column(
        modifier = Modifier.fillMaxWidth()
            .border(1.dp, Color.Green)
            .padding(10.dp)
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
            Spacer(modifier = Modifier.padding(10.dp))
            TextButton(
                modifier = Modifier
                    .background(Color.Black)
                    .size(width = 150.dp, height = 70.dp),
                onClick = onClearClick,
                enabled = !startState
            ) {
                if (startState) {
                    Text(
                        color = Color.Red,
                        text = "CLEAR",
                        fontSize = 30.sp
                    )
                } else {
                    Text(
                        color = Color.White,
                        text = "CLEAR",
                        fontSize = 30.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TimerComponentPreview() {
    TimerAppTheme {
        Surface {
            TimerComponent(
                timerText = "00:00",
                startState = true,
                onStartToggleClick = {},
                onClearClick = {}
            )
        }
    }
}