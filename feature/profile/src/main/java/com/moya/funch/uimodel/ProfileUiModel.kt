package com.moya.funch.uimodel

import com.moya.funch.entity.Blood
import com.moya.funch.entity.Club
import com.moya.funch.entity.Job
import com.moya.funch.entity.SubwayStation

data class ProfileUiModel(
    val name: String = "",
    val job: Job = Job.IDLE,
    val clubs: List<Club> = emptyList(),
    val eOrI: MbtiItem = MbtiItem.E,
    val nOrS: MbtiItem = MbtiItem.N,
    val tOrF: MbtiItem = MbtiItem.T,
    val jOrP: MbtiItem = MbtiItem.J,
    val bloodType: Blood = Blood.A,
    val subway: String = "",
    val subwayTextFieldState: SubwayTextFieldState = SubwayTextFieldState.Empty,
    val subwayStations: List<SubwayStation> = emptyList()
) {
    val isButtonEnabled: Boolean
        get() = name.isNotBlank() &&
            job != Job.IDLE &&
            clubs.isNotEmpty() &&
            subwayTextFieldState == SubwayTextFieldState.Success
}

sealed class SubwayTextFieldState {
    data object Empty : SubwayTextFieldState()
    data object Error : SubwayTextFieldState()
    data object Success : SubwayTextFieldState()
    data object Typing : SubwayTextFieldState()
}
