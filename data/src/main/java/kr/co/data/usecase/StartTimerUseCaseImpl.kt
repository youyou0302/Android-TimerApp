package kr.co.data.usecase

import kotlinx.coroutines.flow.Flow
import kr.co.data.work.WorkTimer
import kr.co.data.work.WorkTimer2
import kr.co.domain.data.Timer1
import kr.co.domain.data.Timer2
import kr.co.domain.data.UseTimer
import kr.co.domain.usecase.StartTimerUseCase
import javax.inject.Inject

class StartTimerUseCaseImpl @Inject constructor() : StartTimerUseCase {

    override suspend fun invoke(timer: UseTimer): Result<Flow<Int>> {
        return when (timer) {
            is Timer1 -> Result.success(WorkTimer.start())
            is Timer2 -> Result.success(WorkTimer2.start())
        }
    }
}