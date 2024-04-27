package kr.co.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.data.usecase.ClearTimerUseCaseImpl
import kr.co.data.usecase.StartTimerUseCaseImpl
import kr.co.data.usecase.StopTimerUseCaseImpl
import kr.co.domain.usecase.ClearTimerUseCase
import kr.co.domain.usecase.StartTimerUseCase
import kr.co.domain.usecase.StopTimerUseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class TimerModule {

    @Binds
    abstract fun bindStartTimerUseCase(uc: StartTimerUseCaseImpl): StartTimerUseCase

    @Binds
    abstract fun bindStopTimerUseCase(uc: StopTimerUseCaseImpl): StopTimerUseCase

    @Binds
    abstract fun bindClearTimerUseCase(uc: ClearTimerUseCaseImpl): ClearTimerUseCase
}