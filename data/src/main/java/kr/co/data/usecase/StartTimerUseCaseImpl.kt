package kr.co.data.usecase

import kotlinx.coroutines.flow.Flow
import kr.co.data.work.WorkTimer
import kr.co.domain.usecase.StartTimerUseCase
import javax.inject.Inject

class StartTimerUseCaseImpl @Inject constructor(): StartTimerUseCase {

    override suspend fun invoke(): Result<Flow<Int>> {
        return Result.success(WorkTimer.start())
    }
}