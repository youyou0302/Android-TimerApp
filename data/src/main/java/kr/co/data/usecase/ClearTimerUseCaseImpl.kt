package kr.co.data.usecase

import kr.co.data.work.WorkTimer
import kr.co.data.work.WorkTimer2
import kr.co.domain.data.Timer1
import kr.co.domain.data.Timer2
import kr.co.domain.data.UseTimer
import kr.co.domain.usecase.ClearTimerUseCase
import javax.inject.Inject

class ClearTimerUseCaseImpl @Inject constructor() : ClearTimerUseCase {

    override suspend fun invoke(timer: UseTimer) {
        when (timer) {
            is Timer1 -> WorkTimer.clear()
            is Timer2 -> WorkTimer2.clear()
        }
    }
}