package com.valance.petproject.presentation.state

import com.valance.petproject.domain.model.SubjectCard

sealed class SubjectCardViewState {
    data object Loading : SubjectCardViewState()
    data class Success(val subject: List<SubjectCard>) : SubjectCardViewState()
    data class Error(val message: String) : SubjectCardViewState()
}