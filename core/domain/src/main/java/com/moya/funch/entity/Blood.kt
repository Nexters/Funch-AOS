package com.moya.funch.entity

enum class Blood(val type: String) {
    A("A형"),
    B("B형"),
    AB("AB형"),
    O("O형"),
    IDLE("idle")
    ;

    companion object {
        fun formType(type: String): Blood {
            return entries.find { it.type == type } ?: IDLE
        }
    }
}
