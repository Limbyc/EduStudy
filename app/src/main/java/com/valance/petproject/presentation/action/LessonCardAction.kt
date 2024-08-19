package com.valance.petproject.presentation.action

import com.valance.petproject.domain.model.LessonCard

sealed interface LessonCardAction {
    data object  Init: LessonCardAction
    data class QueryChanged(val query: String) : LessonCardAction
    data class LessonsLoaded(val lessons: List<LessonCard>) : LessonCardAction
    data class LoadError(val error: String) : LessonCardAction
}