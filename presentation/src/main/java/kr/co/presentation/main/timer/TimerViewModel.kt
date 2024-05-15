package kr.co.presentation.main.timer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kr.co.domain.data.Timer1
import kr.co.domain.data.Timer2
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

    fun startToggleClick() = intent {
        if (state.startState) {
            stopTimerUseCase.invoke(Timer1)
        } else {
            viewModelScope.launch {
                val time: Flow<Int> = startTimerUseCase.invoke(Timer1).getOrThrow()
                time.collect { data ->
                    val sec = (data % 60)
                    val min = data / 60

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
        clearTimerUseCase.invoke(Timer1)
        reduce {
            state.copy(
                timerText = "00:00"
            )
        }
    }

    fun startToggleClick2() = intent {
        if (state.startState2) {
            stopTimerUseCase.invoke(Timer2)
        } else {
            viewModelScope.launch {
                val time: Flow<Int> = startTimerUseCase.invoke(Timer2).getOrThrow()
                time.collect { data ->
                    val sec = (data % 60)
                    val min = data / 60

                    Log.d("TimerViewModel", "${"%02d".format(min)}:${"%02d".format(sec)}")

                    reduce {
                        state.copy(
                            timerText2 = "${"%02d".format(min)}:${"%02d".format(sec)}"
                        )
                    }
                }
            }
        }

        reduce {
            state.copy(
                startState2 = !state.startState2
            )
        }
    }

    fun clearClick2() = intent {
        clearTimerUseCase.invoke(Timer2)
        reduce {
            state.copy(
                timerText2 = "00:00"
            )
        }
    }
}

data class TimerState(
    val timerText: String = "00:00",
    val timerText2: String = "00:00",
    val startState: Boolean = false,
    val startState2: Boolean = false
)

sealed interface TimerSideEffect {
    class Toast(val message: String) : TimerSideEffect
}