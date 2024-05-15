package kr.co.domain.data

sealed class UseTimer

data object Timer1 : UseTimer()
data object Timer2 : UseTimer()