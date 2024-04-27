package kr.co.data.usecase

import kr.co.data.work.WorkTimer
import kr.co.domain.usecase.StopTimerUseCase
import javax.inject.Inject

class StopTimerUseCaseImpl @Inject constructor(): StopTimerUseCase {

    override suspend fun invoke() {
        WorkTimer.stop()
    }
}