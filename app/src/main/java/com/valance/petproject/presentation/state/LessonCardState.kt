package com.valance.petproject.presentation.state

import com.valance.petproject.domain.model.LessonCard

data class LessonCardState (
    val query: String = "",
    val item: List<LessonCard> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = true,
)