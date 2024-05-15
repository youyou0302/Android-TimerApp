package kr.co.domain.usecase

import kotlinx.coroutines.flow.Flow
import kr.co.domain.data.UseTimer

interface StartTimerUseCase {

    suspend operator fun invoke(timer: UseTimer): Result<Flow<Int>>
}