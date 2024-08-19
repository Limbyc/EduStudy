package com.valance.petproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.valance.petproject.presentation.feature.SubjectCardFeature
import com.valance.petproject.presentation.intent.SubjectCardIntent
import com.valance.petproject.presentation.state.SubjectCardViewState
import kotlinx.coroutines.flow.StateFlow

class SubjectCardViewModel(
    private val subjectCardFeature: SubjectCardFeature
) : ViewModel() {

    val state: StateFlow<SubjectCardViewState> = subjectCardFeature.viewStateFlow

    init {
        subjectCardFeature.onAction(SubjectCardIntent.LoadLessonCard(""))
    }

    fun handleIntent(intent: SubjectCardIntent) {
        subjectCardFeature.onAction(intent)
    }
}
