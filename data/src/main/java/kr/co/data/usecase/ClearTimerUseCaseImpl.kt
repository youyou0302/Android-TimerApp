package kr.co.data.usecase

import kr.co.data.work.WorkTimer
import kr.co.domain.usecase.ClearTimerUseCase
import javax.inject.Inject

class ClearTimerUseCaseImpl @Inject constructor(): ClearTimerUseCase {

    override suspend fun invoke() {
        WorkTimer.clear()
    }
}