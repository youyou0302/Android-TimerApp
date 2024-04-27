package kr.co.presentation.main.timer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kr.co.domain.usecase.ClearTimerUseCase
import kr.co.domain.usecase.StartTimerUseCase
import kr.co.domain.usecase.StopTimerUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(
    private val startTimerUseCase: StartTimerUseCase,
    private val stopTimerUseCase: StopTimerUseCase,
    private val clearTimerUseCase: ClearTimerUseCase
) : ViewModel(), ContainerHost<TimerState, TimerSideEffect> {

    override val container: Container<TimerState, TimerSideEffect> =
        container(
            initialState = TimerState(),
            buildSettings = {
                this.exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                    intent { postSideEffect(TimerSideEffect.Toast(throwable.message.orEmpty())) }
                }
            }
        )

    @OptIn(DelicateCoroutinesApi::class)
    fun startToggleClick() = intent {
        if (state.startState) {
            stopTimerUseCase.invoke()
        } else {
            viewModelScope.launch {
                val time: Flow<Int> = startTimerUseCase.invoke().getOrThrow()
                time.collect { time ->
                    val sec = (time % 60)
                    val min = time / 60

                    Log.d("TimerViewModel", "${"%02d".format(min)}:${"%02d".format(sec)}")

                    reduce {
                        state.copy(
                            timerText = "${"%02d".format(min)}:${"%02d".format(sec)}"
                        )
                    }
                }
            }
        }

        reduce {
            state.copy(
                startState = !state.startState
            )
        }
    }

    fun clearClick() = intent {
        clearTimerUseCase.invoke()
        reduce {
            state.copy(
                timerText = "00:00"
            )
        }
    }
}

data class TimerState(
    val timerText: String = "00:00",
    val startState: Boolean = false
)

sealed interface TimerSideEffect {
    class Toast(val message: String) : TimerSideEffect
}