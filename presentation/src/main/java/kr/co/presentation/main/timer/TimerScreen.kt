package kr.co.presentation.main.timer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.hilt.navigation.compose.hiltViewModel
import kr.co.presentation.theme.TimerAppTheme
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun TimerScreen(viewModel: TimerViewModel = hiltViewModel()) {
    val state = viewModel.collectAsState().value

    TimerScreen(
        timerText = state.timerText,
        startState = state.startState,
        onStartToggleClick = viewModel::startToggleClick,
        onClearClick = viewModel::clearClick
    )
}

@Composable
fun TimerScreen(
    timerText: String,
    startState: Boolean,
    onStartToggleClick: () -> Unit,
    onClearClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = timerText,
            fontSize = 100.sp
        )
        Row(
            modifier = Modifier.fillMaxSize(),
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
fun TimerScreenPreview() {
    TimerAppTheme {
        Surface {
            TimerScreen(
                timerText = "00:00",
                startState = true,
                onStartToggleClick = {},
                onClearClick = {}
            )
        }
    }
}