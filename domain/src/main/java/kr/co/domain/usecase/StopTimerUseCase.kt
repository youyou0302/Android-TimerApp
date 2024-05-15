package kr.co.domain.usecase

import kr.co.domain.data.UseTimer

interface StopTimerUseCase {

    suspend operator fun invoke(timer: UseTimer)
}