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
import com.moya.funch.match.model.MatchProfileUiModel
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

    private val matchCode: StateFlow<String> = savedStateHandle.getStateFlow(MATCH_CODE, "")

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
                    MatchUiState.Success(MatchProfileUiModel.from(it), it.similarity, it.chemistrys, it.subwayChemistry)
                } ?: MatchUiState.Error
        }
    }.catch {
        Timber.e("it")
        emit(MatchUiState.Error)
    }.stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MatchUiState.Loading
    )

    fun saveMatchCode(code: String) {
        savedStateHandle[MATCH_CODE] = code
    }

    internal companion object {
        private const val MATCH_CODE = "matchCode"

        val MOCK_MATCHING = Matching(
            profile = Profile().copy(
                name = "abc",
                job = Job.DEVELOPER,
                clubs = listOf(Club.SOPT, Club.NEXTERS),
                mbti = Mbti.INFP,
                blood = Blood.A,
                subways = listOf(
                    SubwayStation("목동역", lines = listOf(SubwayLine.FIVE))
                )
            ),
            similarity = 80,
            chemistrys = listOf(
                Chemistry(
                    title = "찾았다, 내 소울메이트!",
                    description = "ENTJ인 {userName}님은 비전을 향해 적극적으로 이끄는 리더 타입!"
                ),
                Chemistry(
                    title = "서로 다른 점을 찾는 재미",
                    description = "B형인 {userName}님은 호기심과 창의력을 갖췄지만 변덕스러워요"
                )
            ),
            recommends = listOf(
                Recommend("개발자"),
                Recommend("SOPT"),
                Recommend("ENFJ"),
                Recommend("A형"),
                Recommend("목동역")
            ),
            subwayChemistry = Chemistry(
                title = "FIVE",
                description = "5호선"
            )
        )
    }
}

internal sealed class MatchUiState {
    data object Loading : MatchUiState()
    data object Error : MatchUiState()
    data class Success(
        val profile: MatchProfileUiModel,
        val similarity: Int = 0,
        val chemistrys: List<Chemistry> = emptyList(),
        val subWayChemistry: Chemistry? = null
    ) : MatchUiState()
}
