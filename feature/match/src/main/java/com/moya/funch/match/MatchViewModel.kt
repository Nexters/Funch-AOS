package com.moya.funch.match

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moya.funch.entity.Blood
import com.moya.funch.entity.Club
import com.moya.funch.entity.Job
import com.moya.funch.entity.Mbti
import com.moya.funch.entity.SubwayLine
import com.moya.funch.entity.SubwayStation
import com.moya.funch.entity.match.Chemistry
import com.moya.funch.entity.match.Matching
import com.moya.funch.entity.match.Recommend
import com.moya.funch.entity.profile.Profile
import com.moya.funch.usecase.MatchProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber

@HiltViewModel
internal class MatchViewModel @Inject constructor(
    private val matchProfileUseCase: MatchProfileUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val matchCode: StateFlow<String> = savedStateHandle.getStateFlow(MATCH_CODE, "")

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<MatchUiState> = matchCode.mapLatest { code ->
        delay(2400)
        if (code.isEmpty()) {
            MatchUiState.Loading
        } else {
            matchProfileUseCase(matchCode.value)
                .onFailure {
                    Timber.e("MatchViewModel - matchProfileUseCase  - ${it.stackTraceToString()}")
                }
                .getOrNull()
                ?.let {
                    MatchUiState.Success(it)
                } ?: MatchUiState.Error
        }
    }.catch {
        emit(MatchUiState.Error)
    }.stateIn(viewModelScope, started = SharingStarted.WhileSubscribed(5000), initialValue = MatchUiState.Loading)

    fun saveMatchCode(code: String) {
        savedStateHandle[MATCH_CODE] = code
    }

    companion object {
        private const val MATCH_CODE = "matchCode"

        private val MOCK_MATCHING = Matching(
            profile = Profile().copy(
                name = "abc",
                job = Job.DEVELOPER,
                clubs = listOf(Club.NEXTERS),
                mbti = Mbti.INFP,
                blood = Blood.A,
                subways = listOf(
                    SubwayStation("목동역", lines = listOf(SubwayLine.FIVE))
                )
            ),
            similarity = 80,
            chemistrys = listOf(
                Chemistry("대한민국 선수분들", "정말 고생 많으셨습니다...")
            ),
            recommends = listOf(
                Recommend("지금은"),
                Recommend("새벽"),
                Recommend("3시"),
                Recommend("48뷴")
            )
        )
    }
}

internal sealed class MatchUiState {
    data object Loading : MatchUiState()
    data object Error : MatchUiState()
    data class Success(val matching: Matching) : MatchUiState()
}
