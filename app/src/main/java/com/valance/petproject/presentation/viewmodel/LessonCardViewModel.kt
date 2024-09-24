package com.valance.petproject.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.valance.petproject.presentation.feature.LessonCardFeature
import com.valance.petproject.presentation.intent.LessonCardIntent
import com.valance.petproject.presentation.state.LessonCardViewState
import com.valance.petproject.presentation.ui.components.getCurrentWeekStartDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

class LessonCardViewModel(
    private val lessonCardFeature: LessonCardFeature
) : ViewModel() {

    val state: StateFlow<LessonCardViewState> = lessonCardFeature.viewStateFlow
    private val _currentWeekStartDate = MutableStateFlow(getCurrentWeekStartDate())
    val currentWeekStartDate: StateFlow<LocalDate> = _currentWeekStartDate.asStateFlow()
    var selectedDate = mutableStateOf<LocalDate?>(null)

    init {
        lessonCardFeature.onAction(LessonCardIntent.LoadLessonCard(""))
    }

    fun handleIntent(intent: LessonCardIntent) {
        lessonCardFeature.onAction(intent)
    }

    fun updateWeekStartDate(newDate: LocalDate) {
        _currentWeekStartDate.value = newDate
    }

    fun setDate(date: LocalDate) {
        selectedDate.value = date
    }
}

