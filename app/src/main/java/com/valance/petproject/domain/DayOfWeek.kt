package com.valance.petproject.domain

enum class DayOfWeek(val shortName: String) {
    SUNDAY("S"),
    MONDAY("M"),
    TUESDAY("T"),
    WEDNESDAY("W"),
    THURSDAY("T"),
    FRIDAY("F"),
    SATURDAY("S");

    companion object {
        fun getShortNames(): List<String> = entries.map { it.shortName }
    }
}
