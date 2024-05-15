package kr.co.presentation.main.timer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kr.co.presentation.main.timer.component.TimerComponent
import kr.co.presentation.theme.TimerAppTheme
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun TimerScreen(viewModel: TimerViewModel = hiltViewModel()) {
    val state = viewModel.collectAsState().value

    TimerScreen(
        timerText = state.timerText,
        startState = state.startState,
        onStartToggleClick = viewModel::startToggleClick,
        onClearClick = viewModel::clearClick,
        timerText2 = state.timerText2,
        startState2 = state.startState2,
        onStartToggleClick2 = viewModel::startToggleClick2,
        onClearClick2 = viewModel::clearClick2
    )
}

@Composable
fun TimerScreen(
    timerText: String,
    startState: Boolean,
    onStartToggleClick: () -> Unit,
    onClearClick: () -> Unit,
    timerText2: String,
    startState2: Boolean,
    onStartToggleClick2: () -> Unit,
    onClearClick2: () -> Unit
) {
    Column {
        TimerComponent(
            timerText = timerText,
            startState = startState,
            onStartToggleClick = onStartToggleClick,
            onClearClick = onClearClick
        )
        Spacer(
            modifier = Modifier.padding(10.dp)
        )
        TimerComponent(
            timerText = timerText2,
            startState = startState2,
            onStartToggleClick = onStartToggleClick2,
            onClearClick = onClearClick2
        )
    }
}

@Preview
@Composable
fun TimerScreenPreview() {
    TimerAppTheme {
        Surface {
            TimerScreen(
                timerText = "00:00.00",
                startState = true,
                onStartToggleClick = {},
                onClearClick = {},
                timerText2 = "00:00.00",
                startState2 = false,
                onStartToggleClick2 = {},
                onClearClick2 = {}
            )
        }
    }
}