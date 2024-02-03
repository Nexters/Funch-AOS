package com.moya.funch.entity

enum class Mbti {
    ENFJ, ENFP, ENTJ, ENTP,
    ESFJ, ESFP, ESTJ, ESTP,
    INFJ, INFP, INTJ, INTP,
    ISFJ, ISFP, ISTJ, ISTP,
    IDLE;

    companion object {
        fun find(mbti: String): Mbti {
            return requireNotNull(entries.find { it.name == mbti }) { "Mbti : $mbti not found" }
        }
    }
}
