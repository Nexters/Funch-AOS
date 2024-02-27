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
import com.moya.funch.uimodel.SubwayTextFieldState
import com.moya.funch.usecase.CreateUserProfileUseCase
import com.moya.funch.usecase.LoadSubwayStationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CreateProfileUiState(
    val profile: ProfileUiModel = ProfileUiModel(),
    val isLoading: Boolean = false
)

internal sealed class CreateProfileEvent {
    data class NavigateToHome(val message: String) : CreateProfileEvent()
    data class ShowError(val message: String) : CreateProfileEvent()
}

@HiltViewModel
internal class CreateProfileViewModel @Inject constructor(
    private val createUserProfileUseCase: CreateUserProfileUseCase,
    private val loadSubwayStationsUseCase: LoadSubwayStationsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(CreateProfileUiState())
    val uiState: StateFlow<CreateProfileUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<CreateProfileEvent>()
    val event = _event.asSharedFlow()

    fun setNickname(nickname: String) {
        _uiState.value = _uiState.value.copy(
            profile = _uiState.value.profile.copy(
                name = nickname
            )
        )
    }

    fun setJob(job: Job) {
        _uiState.value = _uiState.value.copy(
            profile = _uiState.value.profile.copy(
                job = job
            )
        )
    }

    fun setClub(club: Club) {
        _uiState.value = _uiState.value.copy(
            profile = _uiState.value.profile.copy(
                clubs = _uiState.value.profile.clubs.toggleElement(club)
            )
        )
    }

    fun setBloodType(blood: Blood) {
        _uiState.value = _uiState.value.copy(
            profile = _uiState.value.profile.copy(
                bloodType = blood
            )
        )
    }

    fun setMbti(item: MbtiItem) {
        viewModelScope.launch {
            when (item) {
                MbtiItem.E, MbtiItem.I -> _uiState.update { uiModel ->
                    uiModel.copy(profile = uiModel.profile.copy(eOrI = item))
                }

                MbtiItem.N, MbtiItem.S -> _uiState.update { uiModel ->
                    uiModel.copy(profile = uiModel.profile.copy(nOrS = item))
                }

                MbtiItem.T, MbtiItem.F -> _uiState.update { uiModel ->
                    uiModel.copy(profile = uiModel.profile.copy(tOrF = item))
                }

                MbtiItem.J, MbtiItem.P -> _uiState.update { uiModel ->
                    uiModel.copy(profile = uiModel.profile.copy(jOrP = item))
                }
            }
        }
    }

    fun setSubwayName(subway: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                profile = _uiState.value.profile.copy(
                    subway = subway
                )
            )
            if (subway.isBlank()) {
                setSubwayTextFieldState(SubwayTextFieldState.Empty)
                setSubwayStations(emptyList())
            } else {
                loadSubwayStationsUseCase(subway).fold(
                    onSuccess = { response ->
                        val newState = when {
                            response.isEmpty() -> SubwayTextFieldState.Error
                            subway == response.first().name -> SubwayTextFieldState.Success
                            else -> SubwayTextFieldState.Typing
                        }
                        setSubwayStations(response)
                        setSubwayTextFieldState(newState)
                    },
                    onFailure = {
                        setSubwayTextFieldState(SubwayTextFieldState.Error)
                        setSubwayStations(emptyList())
                    }
                )
            }
        }
    }

    private fun setSubwayTextFieldState(state: SubwayTextFieldState) {
        _uiState.value = _uiState.value.copy(
            profile = _uiState.value.profile.copy(
                subwayTextFieldState = state
            )
        )
    }

    private fun setSubwayStations(stations: List<SubwayStation>) {
        _uiState.value = _uiState.value.copy(
            profile = _uiState.value.profile.copy(
                subwayStations = stations
            )
        )
    }

    fun createProfile() {
        viewModelScope.launch {
            _uiState.update { currentState -> currentState.copy(isLoading = true) }
            val profile = Profile(
                name = _uiState.value.profile.name,
                job = _uiState.value.profile.job,
                clubs = _uiState.value.profile.clubs,
                mbti = Mbti.valueOf(
                    _uiState.value.profile.eOrI.name +
                        _uiState.value.profile.nOrS.name +
                        _uiState.value.profile.tOrF.name +
                        _uiState.value.profile.jOrP.name
                ),
                blood = _uiState.value.profile.bloodType,
                subways = listOf(
                    SubwayStation(
                        name = _uiState.value.profile.subway
                    )
                )
            )
            createUserProfileUseCase(profile).onSuccess {
                _event.emit(CreateProfileEvent.NavigateToHome("프로필을 생성했어요"))
            }.onFailure {
                _uiState.update { currentState -> currentState.copy(isLoading = false) }
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
