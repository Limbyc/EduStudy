package com.valance.petproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.valance.petproject.presentation.feature.LessonCardFeature
import com.valance.petproject.presentation.intent.LessonCardIntent
import com.valance.petproject.presentation.state.LessonCardViewState
import kotlinx.coroutines.flow.StateFlow

class LessonCardViewModel(
    private val lessonCardFeature: LessonCardFeature
) : ViewModel() {

    val state: StateFlow<LessonCardViewState> = lessonCardFeature.viewStateFlow

    init {
        lessonCardFeature.onAction(LessonCardIntent.LoadLessonCard(""))
    }

    fun handleIntent(intent: LessonCardIntent) {
        lessonCardFeature.onAction(intent)
    }
}

