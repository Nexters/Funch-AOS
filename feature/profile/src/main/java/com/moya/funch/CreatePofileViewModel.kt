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
import com.moya.funch.uimodel.ProfileUiModel
import com.moya.funch.usecase.CreateUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal sealed class CreateProfileUiState {
    data object Loading : CreateProfileUiState()
    data object Enabled : CreateProfileUiState()
    data object Disabled : CreateProfileUiState()
}

internal sealed class CreateProfileEvent {
    data object NavigateToHome : CreateProfileEvent()
    data class ShowError(val message: String) : CreateProfileEvent()
}

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
internal class CreateProfileViewModel @Inject constructor(
    private val createUserProfileUseCase: CreateUserProfileUseCase
) : ViewModel() {

    private val _profile = MutableStateFlow(ProfileUiModel())
    val profile = _profile.asStateFlow()

    private val _uiState = MutableStateFlow<CreateProfileUiState>(CreateProfileUiState.Disabled)
    val uiState: StateFlow<CreateProfileUiState> = _profile.mapLatest { uiModel ->
        if (isCreateProfile(uiModel)) {
            CreateProfileUiState.Enabled
        } else {
            CreateProfileUiState.Disabled
        }
    }.stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CreateProfileUiState.Disabled
    )

    private val _event = MutableSharedFlow<CreateProfileEvent>()
    val event = _event.asSharedFlow()

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
        _profile.value = _profile.value.copy(bloodType = blood)
    }

    fun setMbti(item: MbtiItem) {
        viewModelScope.launch {
            when (item) {
                MbtiItem.E, MbtiItem.I -> _profile.update { uiModel -> uiModel.copy(eOrI = item) }
                MbtiItem.N, MbtiItem.S -> _profile.update { uiModel -> uiModel.copy(nOrS = item) }
                MbtiItem.T, MbtiItem.F -> _profile.update { uiModel -> uiModel.copy(tOrF = item) }
                MbtiItem.J, MbtiItem.P -> _profile.update { uiModel -> uiModel.copy(jOrP = item) }
            }
        }
    }

    fun setSubwayName(subway: String) {
        _profile.value = _profile.value.copy(subway = subway)
    }

    private fun isCreateProfile(profile: ProfileUiModel): Boolean {
        return profile.name.isNotBlank() &&
            profile.job != Job.IDLE &&
            profile.clubs.isNotEmpty() &&
            profile.subway.isNotBlank()
    }

    fun createProfile() {
        viewModelScope.launch {
            _uiState.update { CreateProfileUiState.Loading }
            val profile = Profile(
                name = _profile.value.name,
                job = _profile.value.job,
                clubs = _profile.value.clubs,
                mbti = Mbti.valueOf(
                    _profile.value.eOrI.name +
                        _profile.value.nOrS.name +
                        _profile.value.tOrF.name +
                        _profile.value.jOrP.name
                ),
                blood = _profile.value.bloodType,
                subways = listOf(
                    SubwayStation(
                        name = _profile.value.subway
                    )
                )
            )
            createUserProfileUseCase(profile).onSuccess {
                _event.emit(CreateProfileEvent.NavigateToHome)
            }.onFailure {
                _uiState.update { CreateProfileUiState.Enabled }
                _event.emit(CreateProfileEvent.ShowError(it.message ?: "Error"))
            }
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
