package com.moya.funch.entity

enum class Blood(val type: String) {
    A("A형"),
    B("B형"),
    AB("AB형"),
    O("O형"),
    IDLE("idle")
    ;

    companion object {
        fun of(bloodType: String): Blood {
            val blood = runCatching { Blood.valueOf(bloodType) }
            if (blood.isSuccess) return requireNotNull(blood.getOrNull())
            return requireNotNull(Blood.entries.find { it.type == bloodType }) { "Club : $bloodType not found" }
        }
    }
}
