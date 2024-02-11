package com.moya.funch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moya.funch.entity.Blood
import com.moya.funch.entity.Club
import com.moya.funch.entity.Job
import com.moya.funch.entity.SubwayStation
import com.moya.funch.entity.profile.Profile
import com.moya.funch.uimodel.MbtiItem
import com.moya.funch.usecase.CreateUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CreateProfileViewModel @Inject constructor(
    //private val createUserProfileUseCase: CreateUserProfileUseCase
) : ViewModel() {

    private val _profile = MutableStateFlow(Profile())
    val profile = _profile.asStateFlow()

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

    fun setMbti(mbti: MbtiItem) {
        //_profile.value = _profile.value.copy(mbti = mbti)
    }

    fun setSubwayName(subway: String) {
        _profile.value = _profile.value.copy(
            subways =
            listOf(
                SubwayStation(name = subway)
            )
        )
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

fun <T> List<T>.toggleElement(element: T): List<T> {
    return if (contains(element)) {
        filterNot { it == element }
    } else {
        this + element
    }
}

sealed class CreateProfileState {
    data object Loading : CreateProfileState()
    data object Success : CreateProfileState()
    data object Error : CreateProfileState()
}
