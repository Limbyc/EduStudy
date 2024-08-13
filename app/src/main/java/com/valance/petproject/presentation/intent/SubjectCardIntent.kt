package com.valance.petproject.presentation.intent

sealed class SubjectCardIntent {
    data class LoadLessonCard(val cardId: String) : SubjectCardIntent()
}