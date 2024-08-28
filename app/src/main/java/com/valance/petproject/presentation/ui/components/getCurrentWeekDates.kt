package com.valance.petproject.presentation.ui.components

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.time.temporal.WeekFields
import java.util.Locale

fun getCurrentWeekDates(): List<LocalDate> {
    val today = LocalDate.now()
    val weekFields = WeekFields.of(Locale.getDefault())
    val startOfWeek = today.with(weekFields.dayOfWeek(), 1)
    return (0..6).map { startOfWeek.plus(it.toLong(), ChronoUnit.DAYS) }
}
