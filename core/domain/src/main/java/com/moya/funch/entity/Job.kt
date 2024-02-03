package com.moya.funch.entity

enum class Job(val krName: String) {
    DEVELOPER("개발자"), DESIGNER("디자이너"), IDLE("");

    companion object {
        fun find(krName: String): Job {
            return requireNotNull(entries.find { it.krName == krName }) { "Job : $krName not found" }
        }
    }
}
