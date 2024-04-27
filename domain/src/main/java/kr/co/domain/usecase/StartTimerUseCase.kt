package kr.co.domain.usecase

import kotlinx.coroutines.flow.Flow

interface StartTimerUseCase {

    suspend operator fun invoke(): Result<Flow<Int>>
}