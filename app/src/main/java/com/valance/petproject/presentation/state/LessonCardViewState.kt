package com.valance.petproject.presentation.state

import com.valance.petproject.domain.model.LessonCard

sealed class LessonCardViewState {
    data object Loading : LessonCardViewState()
    data class Success(val lessons: List<LessonCard>) : LessonCardViewState()
    data class Error(val message: String) : LessonCardViewState()
}

