package com.moya.funch.entity.match

import com.moya.funch.entity.Blood
import com.moya.funch.entity.Club
import com.moya.funch.entity.Job
import com.moya.funch.entity.Mbti

data class Recommend(
    private val title: String
) {
    fun toJobOrNull(): Job? {
        return runCatching { Job.of(title) }.getOrNull()
    }

    fun toClubOrNull(): Club? {
        return runCatching { Club.of(title) }.getOrNull()
    }

    fun toBloodOrNull(): Blood? {
        return runCatching { title.toBlood() }.getOrNull()
    }

    fun toMbtiOrNull(): Mbti? {
        return runCatching { Mbti.valueOf(title) }.getOrNull()
    }

    // TODO : 지하철도 들어가게 되는지는 확인 필요 (지하철 서버 api) 나오면 알듯

    // TODO : 머지되면 삭제
    private fun String.toBlood(): Blood {
        val blood = runCatching { Blood.valueOf(this) }
        if (blood.isSuccess) return requireNotNull(blood.getOrNull())
        return requireNotNull(Blood.entries.find { it.type == this }) { "Blood : $this not found" }
    }

}
