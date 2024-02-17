package com.moya.funch.match.model

import com.moya.funch.entity.Blood
import com.moya.funch.entity.Club
import com.moya.funch.entity.Job
import com.moya.funch.entity.Mbti
import com.moya.funch.entity.SubwayStation
import com.moya.funch.entity.match.Recommend
import com.moya.funch.entity.profile.Profile

internal class MatchProfileUiModel private constructor(
    val name: String = "",
    val job: MatchingWrapper<Job> = MatchingWrapper(Job.IDLE, false),
    val clubs: List<MatchingWrapper<Club>> = emptyList(),
    val mbti: MatchingWrapper<Mbti> = MatchingWrapper(Mbti.IDLE, false),
    val blood: MatchingWrapper<Blood> = MatchingWrapper(Blood.IDLE, false),
    val subways: List<MatchingWrapper<SubwayStation>> = emptyList(),
) {

    internal data class MatchingWrapper<T>(val data: T, val matched: Boolean)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MatchProfileUiModel

        if (name != other.name) return false
        if (job != other.job) return false
        if (clubs != other.clubs) return false
        if (mbti != other.mbti) return false
        if (blood != other.blood) return false
        return subways == other.subways
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + job.hashCode()
        result = 31 * result + clubs.hashCode()
        result = 31 * result + mbti.hashCode()
        result = 31 * result + blood.hashCode()
        result = 31 * result + subways.hashCode()
        return result
    }

    override fun toString(): String {
        return "MatchProfileUiModel(name='$name', job=$job, clubs=$clubs, mbti=$mbti, blood=$blood, subways=$subways)"
    }

    companion object {
        fun from(profile: Profile, recommends: List<Recommend>): MatchProfileUiModel {
            with(profile) {
                val job = MatchingWrapper(job, job.isMatch(recommends))
                val clubs = clubs.map { club -> MatchingWrapper(club, club.isMatch(recommends)) }
                val mbti = MatchingWrapper(mbti, mbti.isMatch(recommends))
                val blood = MatchingWrapper(blood, blood.isMatch(recommends))
                val subwayStations = subways.map { subway -> MatchingWrapper(subway, subway.isMatch(recommends)) }
                return MatchProfileUiModel(name = name, job, clubs, mbti, blood, subwayStations)
            }
        }
    }
}

private fun Job.isMatch(recommends: List<Recommend>): Boolean {
    return recommends.any { recommend ->
        runCatching {
            Job.of(recommend.title)
        }.isSuccess
    }
}

private fun Club.isMatch(recommends: List<Recommend>): Boolean {
    return recommends.any { recommend ->
        runCatching { Club.of(recommend.title) }.isSuccess
    }
}

private fun Mbti.isMatch(recommends: List<Recommend>): Boolean {
    return recommends.any { recommend ->
        runCatching { Mbti.valueOf(recommend.title) }.isSuccess
    }
}

private fun Blood.isMatch(recommends: List<Recommend>): Boolean {
    return recommends.any { recommend ->
        runCatching { Blood.of(recommend.title) }.isSuccess
    }
}

private fun SubwayStation.isMatch(recommends: List<Recommend>): Boolean {
    return recommends.any { recommend ->
        runCatching { name == recommend.title }.isSuccess
    }
}
