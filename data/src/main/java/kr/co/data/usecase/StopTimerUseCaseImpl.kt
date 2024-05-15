package kr.co.data.usecase

import kr.co.data.work.WorkTimer
import kr.co.data.work.WorkTimer2
import kr.co.domain.data.Timer1
import kr.co.domain.data.Timer2
import kr.co.domain.data.UseTimer
import kr.co.domain.usecase.StopTimerUseCase
import javax.inject.Inject

class StopTimerUseCaseImpl @Inject constructor() : StopTimerUseCase {

    override suspend fun invoke(timer: UseTimer) {
        when (timer) {
            is Timer1 -> WorkTimer.stop()
            is Timer2 -> WorkTimer2.stop()
        }
    }
}