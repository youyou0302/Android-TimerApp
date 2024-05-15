package kr.co.data.work

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object WorkTimer2 {

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