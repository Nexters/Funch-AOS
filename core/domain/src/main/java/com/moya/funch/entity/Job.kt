package com.moya.funch.entity

enum class Job(val krName: String) {
    DEVELOPER("개발자"),
    DESIGNER("디자이너"),
    IDLE("idle")
    ;

    companion object {
        fun of(name: String): Job {
            val job = runCatching { valueOf(name) }
            if (job.isSuccess) return requireNotNull(job.getOrNull())
            return requireNotNull(entries.find { it.krName == name }) { "Job : $name not found" }
        }
    }
}
