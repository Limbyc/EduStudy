package com.valance.petproject.presentation.action

import com.valance.petproject.domain.model.SubjectCard

sealed interface SubjectCardAction {
    data object  Init: SubjectCardAction
    data class QueryChanged(val query: String) : SubjectCardAction
    data class SubjectLoaded(val subject: List<SubjectCard>) : SubjectCardAction
    data class LoadError(val error: String) : SubjectCardAction
}