package com.moya.funch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moya.funch.entity.Blood
import com.moya.funch.entity.Club
import com.moya.funch.entity.Job
import com.moya.funch.entity.Mbti
import com.moya.funch.entity.SubwayStation
import com.moya.funch.entity.profile.Profile
import com.moya.funch.uimodel.MbtiItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MbtiState(
    val eOrI: MbtiItem = MbtiItem.E,
    val nOrS: MbtiItem = MbtiItem.N,
    val tOrF: MbtiItem = MbtiItem.T,
    val jOrP: MbtiItem = MbtiItem.J
)

@HiltViewModel
internal class CreateProfileViewModel @Inject constructor(
    // private val createUserProfileUseCase: CreateUserProfileUseCase
) : ViewModel() {

    private val _profile = MutableStateFlow(Profile())
    val profile = _profile.asStateFlow()

    private val mbtiState = MutableStateFlow(MbtiState())

    fun setNickname(nickname: String) {
        _profile.value = _profile.value.copy(name = nickname)
    }

    fun setJob(job: Job) {
        _profile.value = _profile.value.copy(job = job)
    }

    fun setClub(club: Club) {
        _profile.value = _profile.value.copy(
            clubs = _profile.value.clubs.toggleElement(club)
        )
    }

    fun setBloodType(blood: Blood) {
        _profile.value = _profile.value.copy(blood = blood)
    }

    fun setMbti(item: MbtiItem) {
        viewModelScope.launch {
            when (item) {
                MbtiItem.E, MbtiItem.I -> mbtiState.update { uiModel -> uiModel.copy(eOrI = item) }
                MbtiItem.N, MbtiItem.S -> mbtiState.update { uiModel -> uiModel.copy(nOrS = item) }
                MbtiItem.T, MbtiItem.F -> mbtiState.update { uiModel -> uiModel.copy(tOrF = item) }
                MbtiItem.J, MbtiItem.P -> mbtiState.update { uiModel -> uiModel.copy(jOrP = item) }
            }
            _profile.value = _profile.value.copy(
                mbti = Mbti.valueOf(
                    mbtiState.value.eOrI.name +
                        mbtiState.value.nOrS.name +
                        mbtiState.value.tOrF.name +
                        mbtiState.value.jOrP.name
                )
            )
        }
    }

    fun isSelectMbti(mbtiItem: MbtiItem): Boolean {
        return when (mbtiItem) {
            MbtiItem.E -> mbtiState.value.eOrI == MbtiItem.E
            MbtiItem.I -> mbtiState.value.eOrI == MbtiItem.I
            MbtiItem.N -> mbtiState.value.nOrS == MbtiItem.N
            MbtiItem.S -> mbtiState.value.nOrS == MbtiItem.S
            MbtiItem.T -> mbtiState.value.tOrF == MbtiItem.T
            MbtiItem.F -> mbtiState.value.tOrF == MbtiItem.F
            MbtiItem.J -> mbtiState.value.jOrP == MbtiItem.J
            MbtiItem.P -> mbtiState.value.jOrP == MbtiItem.P
        }
    }

    fun setSubwayName(subway: String) {
        _profile.value = _profile.value.copy(
            subways =
            listOf(
                SubwayStation(name = subway)
            )
        )
    }

    fun isCreateProfile(profile: Profile): Boolean {
        return profile.job != Job.IDLE &&
            profile.clubs.isNotEmpty() &&
            profile.mbti != Mbti.IDLE &&
            profile.blood != Blood.IDLE &&
            profile.name.isNotBlank() &&
            profile.subways[0].name.isNotBlank()
    }

    fun createProfile() {
        viewModelScope.launch {
            /*createUserProfileUseCase(_profile.value).onSuccess {
                // TODO : navigate to main
            }.onFailure {
                // TODO : show error
            }*/
        }
    }
}

private fun <T> List<T>.toggleElement(element: T): List<T> {
    return if (contains(element)) {
        filterNot { it == element }
    } else {
        this + element
    }
}

internal sealed class CreateProfileState {
    data object Loading : CreateProfileState()
    data object Success : CreateProfileState()
    data object Error : CreateProfileState()
}
