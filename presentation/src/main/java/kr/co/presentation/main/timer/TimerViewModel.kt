package kr.co.presentation.main.timer

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.timer

@HiltViewModel
class TimerViewModel @Inject constructor(

) : ViewModel(), ContainerHost<TimerState, TimerSideEffect> {

    var timer: Timer? = null
    var time = 0

    override val container: Container<TimerState, TimerSideEffect> =
        container(
            initialState = TimerState(),
            buildSettings = {
                this.exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                    intent { postSideEffect(TimerSideEffect.Toast(throwable.message.orEmpty())) }
                }
            }
        )


    init {
        load()
    }

    private fun load() = intent {
        reduce {
            state.copy(
                timerText = "00:00",
                startState = false
            )
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun startToggleClick() = intent {
        if (state.startState) {
            timer?.cancel()
        } else {
            timer = timer(period = 1000) {
                time++
                val sec = (time % 60)
                val min = time / 60

                Log.d("", "${"%02d".format(min)}:${"%02d".format(sec)}")
                GlobalScope.launch {
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
        time = 0
        reduce {
            state.copy(
                timerText = "00:00"
            )
        }
    }
}

data class TimerState(
    val timerText: String = "",
    val startState: Boolean = false
)

sealed interface TimerSideEffect {
    class Toast(val message: String) : TimerSideEffect
}