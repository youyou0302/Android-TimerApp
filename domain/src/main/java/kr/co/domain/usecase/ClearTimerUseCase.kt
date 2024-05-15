package kr.co.domain.usecase

import kr.co.domain.data.UseTimer

interface ClearTimerUseCase {

    suspend operator fun invoke(timer: UseTimer)
}