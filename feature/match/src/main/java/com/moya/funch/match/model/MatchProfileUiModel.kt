package com.moya.funch.match.model

import com.moya.funch.entity.Blood
import com.moya.funch.entity.Club
import com.moya.funch.entity.Job
import com.moya.funch.entity.Mbti
import com.moya.funch.entity.SubwayStation
import com.moya.funch.entity.match.Matching

internal sealed interface ProfileItems {
    val title: String

    enum class LeadingIcon(override val title: String) : ProfileItems {
        JOB("직군"),
        CLUB("동아리")
    }

    enum class TrailingIcon(override val title: String) : ProfileItems {
        SUBWAY("지하철")
    }

    enum class NonIcon(override val title: String) : ProfileItems {
        BLOOD("혈액형"),
        MBTI("MBTI")
    }
}

internal data class MatchingWrapper<T>(val profileItem: ProfileItems, val data: T, val matched: Boolean)

internal data class MatchProfileUiModel(
    val name: String,
    val job: MatchingWrapper<Job>,
    val clubs: List<MatchingWrapper<Club>>,
    val mbti: MatchingWrapper<Mbti>,
    val blood: MatchingWrapper<Blood>,
    val subways: List<MatchingWrapper<SubwayStation>>
) {
    companion object {
        fun from(matching: Matching): MatchProfileUiModel {
            val profile = matching.profile
            val matchInfos = matching.matchInfos
            with(profile) {
                val job = MatchingWrapper(ProfileItems.LeadingIcon.JOB, job, matching.matches(job, matchInfos))
                val clubs =
                    clubs.map { club ->
                        MatchingWrapper(
                            ProfileItems.LeadingIcon.CLUB,
                            club,
                            matching.matches(club, matchInfos)
                        )
                    }
                val mbti = MatchingWrapper(ProfileItems.NonIcon.MBTI, mbti, matching.matches(mbti, matchInfos))
                val blood = MatchingWrapper(ProfileItems.NonIcon.BLOOD, blood, matching.matches(blood, matchInfos))
                val subwayStations =
                    subways.map { subway ->
                        MatchingWrapper(
                            ProfileItems.TrailingIcon.SUBWAY,
                            subway,
                            matching.matches(subway, matchInfos)
                        )
                    }
                return MatchProfileUiModel(name = name, job, clubs, mbti, blood, subwayStations)
            }
        }
    }
}
