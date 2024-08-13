package com.valance.petproject.presentation.intent

sealed class LessonCardIntent {
    data class LoadLessonCard(val cardId: String) : LessonCardIntent()
}