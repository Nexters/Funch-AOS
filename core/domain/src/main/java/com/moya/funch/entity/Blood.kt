package com.moya.funch.entity

enum class Blood {
    A, B, AB, O, IDLE;

    companion object {
        fun find(name: String): Blood {
            return requireNotNull(entries.find { it.name == name }) { "Blood : $name not found" }
        }
    }
}
