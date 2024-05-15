package kr.co.data.work

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object WorkTimer2 {

    private var time = 0
    private var startFlag = false

    fun start(): Flow<Int> = flow {
        startFlag = true
        while (startFlag) {
            Log.d("WorkTimer", "$time")
            emit(time)
            delay(1000L)
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