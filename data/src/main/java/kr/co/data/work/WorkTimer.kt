package kr.co.data.work

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

object WorkTimer {

    private var time = 0
    private var startFlag = false

    fun start(): Flow<Int> = flow {
        startFlag = true
        while (startFlag) {
            emit(time)
            delay(10L)
            time++
        }
    }

    fun stop() {
        startFlag = false
    }

    fun clear() {
        time = 0
    }
}