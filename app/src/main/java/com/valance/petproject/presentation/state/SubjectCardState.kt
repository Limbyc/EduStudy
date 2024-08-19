package com.valance.petproject.presentation.state

import com.valance.petproject.domain.model.SubjectCard

data class SubjectCardState (
    val query: String = "",
    val item: List<SubjectCard> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = true,
)